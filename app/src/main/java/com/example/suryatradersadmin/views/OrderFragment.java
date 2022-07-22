package com.example.suryatradersadmin.views;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.adapter.EmployeeAdapter;
import com.example.suryatradersadmin.adapter.OrderAdapter;
import com.example.suryatradersadmin.databinding.FragmentOrderBinding;
import com.example.suryatradersadmin.databinding.OrderRowBinding;
import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.model.Orders;
import com.example.suryatradersadmin.viewmodel.EmployeeViewModel;
import com.example.suryatradersadmin.viewmodel.OrderViewModel;
import com.example.suryatradersadmin.viewmodel.SubOrderViewModel;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements OrderAdapter.OrderInterface{

    FragmentOrderBinding fragmentOrderBinding;
    OrderViewModel orderViewModel;
    OrderAdapter orderAdapter;
    NavController navController;
    SubOrderViewModel subOrderViewModel;
    EmployeeViewModel employeeViewModel;
    AppCompatSpinner employeeSpinner;
    AppCompatSpinner statusAssignSpinner;

    private static final String TAG="OrderFragment ";

    public OrderFragment() {

    }


    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       fragmentOrderBinding=FragmentOrderBinding.inflate(inflater,container,false);
       return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderAdapter=new OrderAdapter(this);

        fragmentOrderBinding.orderRecyclerview.setAdapter(orderAdapter);
        orderViewModel=new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
            @Override
            public void onChanged(List<Orders> orderList) {
                orderAdapter.submitList(orderList);
            }
        });



        navController= Navigation.findNavController(view);
    }

    @Override
    public void onOrderClick(Integer customerId,Integer orderId) {

        Log.d(TAG, "Customer: "+customerId+" onOrderClick: orderId "+orderId);

        orderViewModel.setSubOrderList(customerId,orderId);

        OrderFragmentDirections.ActionOrderFragmentToSubOrderFragment action = OrderFragmentDirections.actionOrderFragmentToSubOrderFragment(customerId,orderId);
        action.setCustomerId(customerId);
        action.setOrderId(orderId);

        navController.navigate(action);
    }



  /*  public void assignEmployee(final Integer orderId)
    {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        final View empview=getLayoutInflater().inflate(R.layout.employee_spinner,null,false);

        employeeSpinner=empview.findViewById(R.id.empSpinner);
        employeeViewModel=new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        final List<String> empName=new ArrayList<>();
        employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                for (Employee employee:employees)
                {
                    empName.add(employee.getEmployeeFirstName()+" "+employee.getEmployeeLastName());
                }
                ArrayAdapter employeeAdapter=new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,empName);
                employeeSpinner.setAdapter(employeeAdapter);
            }
        });

        View titleView = getLayoutInflater().inflate(R.layout.layout_alert_box_title, null);
        builder.setCustomTitle(titleView);
        builder.setView(empview);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(employeeSpinner.getSelectedItem()!=null) {
                    orderViewModel.assignEmployee(orderId);

                    orderViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                    {
                        public void onChanged(String message)
                        {
                            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(getActivity(),"Please select employee",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.orange));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.orange));


    }*/

    public void changeOrderStatus(final Integer orderId, String orderStatus)
    {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        final View orderStatusView=getLayoutInflater().inflate(R.layout.change_order_status_spinner,null,false);
        statusAssignSpinner=orderStatusView.findViewById(R.id.changeOrderStatusSpinner);

        final List<String> statusAssignList=new ArrayList<>();
        statusAssignList.add("Pending");
        statusAssignList.add("On-Going");
        statusAssignList.add("Dispatched");

        ArrayAdapter statusOrderAdapter=new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,statusAssignList);
        statusAssignSpinner.setAdapter(statusOrderAdapter);

        if(orderStatus!=null) {
            int position = statusOrderAdapter.getPosition(orderStatus);
            statusAssignSpinner.setSelection(position);
        }

        View titleView = getLayoutInflater().inflate(R.layout.change_order_status_title, null);
        builder.setCustomTitle(titleView);
        builder.setView(orderStatusView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(statusAssignSpinner.getSelectedItem()!=null)
                {
                    String orderStatus=statusAssignSpinner.getSelectedItem().toString();
                    orderStatus=orderStatus.replaceAll("-","");
                    orderViewModel.changeStatus(orderId, orderStatus.toLowerCase());

                    orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> orders) {
                                orderAdapter.submitList(orders);
                            }
                        });

                    orderViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                    {
                        public void onChanged(String message)
                        {
                            String msg[];
                            if(message.contains("200")) {
                                msg = message.split(" 200");
                                Toast toast = Toast.makeText(getActivity(), msg[0], Toast.LENGTH_SHORT);
                                View view = toast.getView();
                                view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                                toast.show();
                            }
                            else
                            {
                                msg = message.split(" 1002");
                                Toast toast = Toast.makeText(getActivity(), msg[0], Toast.LENGTH_SHORT);
                                View view = toast.getView();
                                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                                toast.show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(getActivity(),"Please select status",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.orange));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.orange));

    }

    @Override
    public void onReviewClick(Integer orderId) {
        Log.d(TAG,"onReviewClicked ");

        orderViewModel.setReviewList(orderId);
        OrderFragmentDirections.ActionOrderFragmentToReviewFragment action= OrderFragmentDirections.actionOrderFragmentToReviewFragment(orderId.toString());
        action.setOrderId(orderId.toString());
        navController.navigate(action);
    }

    @Override
    public void  onDeleteOrderClick(final Integer orderId) {
        Log.d(TAG," onDeleteOrderClick ");


        Log.d(TAG,"Delete Order ");
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

        final TextView order_delete=new TextView(getContext());
        order_delete.setText("Are you sure you want to delete this order?");
        order_delete.setPadding(20,20,20,20);
        order_delete.setTextSize(20);
        order_delete.setTextColor(Color.parseColor("#000000"));

        builder.setView(order_delete);
        View titleView = getLayoutInflater().inflate(R.layout.delete_order_layout, null);
        builder.setCustomTitle(titleView);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                orderViewModel.deleteOrder(orderId);
                orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer <List<Orders>> (){
                    @Override
                    public void onChanged(List<Orders> orders ) {
                        orderAdapter.submitList(orders);
                    }
                });

                orderViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                {
                    public void onChanged(String message)
                    {
                        String msg[];
                        if(message.contains("200")) {
                            msg = message.split(" 200");
                            Toast toast = Toast.makeText(getActivity(), msg[0], Toast.LENGTH_SHORT);
                            View view = toast.getView();
                            view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                            toast.show();
                        }
                        else
                        {
                            msg = message.split(" 1002");
                            Toast toast = Toast.makeText(getActivity(), msg[0], Toast.LENGTH_SHORT);
                            View view = toast.getView();
                            view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                            toast.show();
                        }
                    }
                });

            }
        });

        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        // builder.show();

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.orange));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.orange));
    }


}