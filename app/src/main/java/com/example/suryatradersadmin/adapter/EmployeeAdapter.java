package com.example.suryatradersadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatradersadmin.databinding.EmployeeRowBinding;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.repository.EmployeeRepository;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;



public class EmployeeAdapter extends ListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder> {

    private static final String TAG="EmployeeAdapter ";
    private EmployeeInterface employeeInterface;

    public EmployeeAdapter(EmployeeInterface employeeInterface)
    {
        super(Employee.itemCallback);
        this.employeeInterface=employeeInterface;

    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        EmployeeRowBinding employeeRowBinding=EmployeeRowBinding.inflate(layoutInflater, parent,false);
        employeeRowBinding.setEmployeeInterface(employeeInterface);
        return new EmployeeViewHolder(employeeRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee =getItem(position);
        holder.employeeRowBinding.setEmployee(employee);

    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        EmployeeRowBinding employeeRowBinding;

        public EmployeeViewHolder(EmployeeRowBinding binding)
        {
            super(binding.getRoot());
            this.employeeRowBinding=binding;
        }


    }


    public interface EmployeeInterface
    {
        public void showModalForEmployeeTypeUpdate(final String employeePhoneNo,String classType);
    }
}
