package com.example.suryatradersadmin.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.adapter.AdminAdapter;
import com.example.suryatradersadmin.adapter.EmployeeAdapter;
import com.example.suryatradersadmin.databinding.FragmentAdminBinding;
import com.example.suryatradersadmin.databinding.FragmentEmployeeBinding;
import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.viewmodel.AdminViewModel;
import com.example.suryatradersadmin.viewmodel.EmployeeViewModel;

import java.util.List;


public class AdminFragment extends Fragment {


    FragmentAdminBinding fragmentAdminBinding;
    AdminAdapter adminAdapter;
    AdminViewModel adminViewModel;
    public AdminFragment() {

    }


    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAdminBinding=FragmentAdminBinding.inflate(inflater,container,false);
        return  fragmentAdminBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adminAdapter=new AdminAdapter();
        fragmentAdminBinding.adminRecyclerview.setAdapter(adminAdapter);
        adminViewModel=new ViewModelProvider(requireActivity()).get(AdminViewModel.class);
        adminViewModel.getAllAdmin().observe(getViewLifecycleOwner(), new Observer<List<Admin>>() {
            @Override
            public void onChanged(List<Admin> admins) {
                adminAdapter.submitList(admins);
            }
        });

    }
}