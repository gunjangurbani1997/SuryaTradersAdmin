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
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.adapter.CustomerOrderDetailListAdapter;
import com.example.suryatradersadmin.adapter.SubOrderAdapter;
import com.example.suryatradersadmin.databinding.FragmentSubOrderBinding;
import com.example.suryatradersadmin.model.CustomerOrderDetail;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.model.SubOrders;
import com.example.suryatradersadmin.viewmodel.CustomerOrderDetailViewModel;
import com.example.suryatradersadmin.viewmodel.EmployeeViewModel;
import com.example.suryatradersadmin.viewmodel.SubOrderViewModel;

import java.util.ArrayList;
import java.util.List;


public class SubOrderFragment extends Fragment implements SubOrderAdapter.SubOrderInterface{

    FragmentSubOrderBinding fragmentSubOrderBinding;
    SubOrderViewModel subOrderViewModel;
    SubOrderAdapter subOrderAdapter;
    AppCompatButton btnAssignEmployee;
    EmployeeViewModel employeeViewModel;
    AppCompatSpinner employeeSpinner;
    Integer customerId = null;
    Integer orderId = null;


    public SubOrderFragment() {

    }


    public static SubOrderFragment newInstance(String param1, String param2) {
        SubOrderFragment fragment = new SubOrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      fragmentSubOrderBinding= FragmentSubOrderBinding.inflate(inflater,container,false);
        if (getArguments() != null) {

            SubOrderFragmentArgs args = SubOrderFragmentArgs.fromBundle(getArguments());
            customerId = args.getCustomerId();
            orderId=args.getOrderId();

        }
      return fragmentSubOrderBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subOrderAdapter = new SubOrderAdapter(this);
        fragmentSubOrderBinding.subOrderRecyclerview.setAdapter(subOrderAdapter);

        subOrderViewModel=new ViewModelProvider(requireActivity()).get(SubOrderViewModel.class);
        subOrderViewModel.getCustomerOrderDetailList(customerId,orderId).observe(getViewLifecycleOwner(), new Observer<List<SubOrders>>() {
            @Override
            public void onChanged(List<SubOrders> subOrdersList) {
                subOrderAdapter.submitList(subOrdersList);
            }
        });

    }


    public void assignEmployee(final Integer subOrderId)
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
                    subOrderViewModel.assignEmployee(subOrderId);

                    subOrderViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
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


    }
}