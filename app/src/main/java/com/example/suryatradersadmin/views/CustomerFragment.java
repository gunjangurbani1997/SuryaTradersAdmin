package com.example.suryatradersadmin.views;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.adapter.AdminAdapter;
import com.example.suryatradersadmin.adapter.CustomerAdapter;
import com.example.suryatradersadmin.databinding.FragmentAdminBinding;
import com.example.suryatradersadmin.databinding.FragmentCustomerBinding;
import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.viewmodel.AdminViewModel;
import com.example.suryatradersadmin.viewmodel.CustomerViewModel;
import com.example.suryatradersadmin.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.List;


public class CustomerFragment extends Fragment implements CustomerAdapter.CustomerOrderDetailInterface{

    FragmentCustomerBinding fragmentCustomerBinding;
    CustomerAdapter customerAdapter;
    CustomerViewModel customerViewModel;
    private static final String TAG="CustomerFragment";
    private NavController navController;
    private Spinner customerClassTypeSpinner;
    LinearLayout linearLayout;


    public CustomerFragment() {
    }


    public static CustomerFragment newInstance(String param1, String param2) {
        CustomerFragment fragment = new CustomerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCustomerBinding=FragmentCustomerBinding.inflate(inflater,container,false);
        return  fragmentCustomerBinding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout=view.findViewById(R.id.empTypeLL);
        customerAdapter=new CustomerAdapter(this);
        fragmentCustomerBinding.customerRecyclerview.setAdapter(customerAdapter);
        customerViewModel=new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                customerAdapter.submitList(customers);
            }
        });
        navController= Navigation.findNavController(view);
    }

    @Override
    public void onCustomerClick(Integer customerId) {

        Log.d(TAG, "onCustomerClicked "+customerId);

        customerViewModel.setCustomerOrderDetailList(customerId);
        CustomerFragmentDirections.ActionCustomerFragmentToCustomerOrderDetailFragment action = CustomerFragmentDirections.actionCustomerFragmentToCustomerOrderDetailFragment(customerId);
        action.setCustomerId(customerId);

        navController.navigate(action);
    }

    @Override
    public void deleteCustomer(final Integer customerId) {
        Log.d(TAG,"Delete Customer ");
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

        final TextView customer_delete=new TextView(getContext());
        customer_delete.setText("Are you sure you want to delete this customer?");
        customer_delete.setPadding(20,20,20,20);
        customer_delete.setTextSize(20);
        customer_delete.setTextColor(Color.parseColor("#000000"));

        builder.setView(customer_delete);
        View titleView = getLayoutInflater().inflate(R.layout.delete_customer_alert, null);
        builder.setCustomTitle(titleView);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                customerViewModel.deleteCustomer(customerId);
                customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer <List<Customer>> (){
                    @Override
                    public void onChanged(List<Customer> customers ) {
                        customerAdapter.submitList(customers);
                    }
                });

                customerViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
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

    public void approveCustomer(final String mobile,String customerCategory)
    {
        final View view=getLayoutInflater().inflate(R.layout.customer_type_spinner,null,false);

        Log.d(TAG,"Approve Employee Type ");
        List<String> customerClassTypeList=new ArrayList<>();
        customerClassTypeList.add("Class A");
        customerClassTypeList.add("Class B");
        customerClassTypeList.add("Class C");

        customerClassTypeSpinner=view.findViewById(R.id.customer_type);
        ArrayAdapter customerClassTypeAdapter=new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,customerClassTypeList);
        customerClassTypeSpinner.setAdapter(customerClassTypeAdapter);

        if(customerCategory!=null) {
            int position = customerClassTypeAdapter.getPosition(customerCategory);
            customerClassTypeSpinner.setSelection(position);
        }


        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Approve Customer");
        View titleView = getLayoutInflater().inflate(R.layout.approve_customer_alert, null);
        builder.setCustomTitle(titleView);
        builder.setView(view);
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(customerClassTypeSpinner.getSelectedItem()!=null) {
                   String customerCategory=customerClassTypeSpinner.getSelectedItem().toString();
                    customerViewModel.approveCustomer(mobile, customerCategory);
                    customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
                        @Override
                        public void onChanged(List<Customer> customers) {
                            customerAdapter.submitList(customers);
                        }
                    });


                    customerViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
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