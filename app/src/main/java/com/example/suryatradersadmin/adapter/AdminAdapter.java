package com.example.suryatradersadmin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatradersadmin.databinding.AdminRowBinding;
import com.example.suryatradersadmin.databinding.EmployeeRowBinding;
import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.Employee;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class AdminAdapter extends ListAdapter<Admin, AdminAdapter.AdminViewHolder> {
    private static final String TAG="AdminAdapter ";

    public AdminAdapter()
    {
        super(Admin.itemCallback);

    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        AdminRowBinding adminRowBinding=AdminRowBinding.inflate(layoutInflater, parent,false);
        return new AdminViewHolder(adminRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        Admin admin =getItem(position);
        holder.adminRowBinding.setAdmin(admin);
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {

        AdminRowBinding adminRowBinding;

        public AdminViewHolder(AdminRowBinding binding)
        {
            super(binding.getRoot());
            this.adminRowBinding=binding;

        }
    }
}
