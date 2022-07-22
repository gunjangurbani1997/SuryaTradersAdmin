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

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.adapter.EmployeeAdapter;
import com.example.suryatradersadmin.databinding.FragmentEmployeeBinding;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.viewmodel.EmployeeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class EmployeeFragment extends Fragment implements EmployeeAdapter.EmployeeInterface{

    FragmentEmployeeBinding fragmentEmployeeBinding;
    EmployeeAdapter employeeAdapter;
    EmployeeViewModel employeeViewModel;
    String employeeType;
    private Spinner empClassTypeSpinner;
    private static final String TAG="EmployeeFragment ";
    public EmployeeFragment() {

    }


    public static EmployeeFragment newInstance(String param1, String param2) {
        EmployeeFragment fragment = new EmployeeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentEmployeeBinding=FragmentEmployeeBinding.inflate(inflater,container,false);
        return  fragmentEmployeeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeAdapter=new EmployeeAdapter(this);
        fragmentEmployeeBinding.employeeRecyclerview.setAdapter(employeeAdapter);
        employeeViewModel=new ViewModelProvider(requireActivity()).get(EmployeeViewModel.class);
        employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
               employeeAdapter.submitList(employees);
            }
        });

    }


    @Override
    public void showModalForEmployeeTypeUpdate(final String employeePhoneNo,String classType) {

       Log.d(TAG,"Update Employee Type");
       final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
       builder.setTitle("Employee Details");

        final View view=getLayoutInflater().inflate(R.layout.class_type_spinner,null,false);

        Log.d(TAG,"Approve Employee Type ");
        List<String> employeeClassTypeList=new ArrayList<>();
        employeeClassTypeList.add("Class A");
        employeeClassTypeList.add("Class B");

        empClassTypeSpinner=view.findViewById(R.id.classType);
        ArrayAdapter empClassTypeAdapter=new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,employeeClassTypeList);
        empClassTypeSpinner.setAdapter(empClassTypeAdapter);

        if(classType!=null) {
            int position = empClassTypeAdapter.getPosition(classType);
            empClassTypeSpinner.setSelection(position);
        }

        View titleView = getLayoutInflater().inflate(R.layout.layout_alert_box_title, null);
        builder.setTitle("Employee Details");
        builder.setCustomTitle(titleView);
        builder.setView(view);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(empClassTypeSpinner.getSelectedItem()!=null) {
                    String employeeType = empClassTypeSpinner.getSelectedItem().toString();
                    employeeViewModel.updateEmployeeType(employeeType,employeePhoneNo);
                    employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
                        @Override
                        public void onChanged(List<Employee> employees) {

                            employeeAdapter.submitList(employees);
                            employeeViewModel.getChangeStatusMsg().observe(getViewLifecycleOwner(), new Observer<String>()
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

       // builder.show();


    }
}