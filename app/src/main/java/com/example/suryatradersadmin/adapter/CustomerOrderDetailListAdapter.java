package com.example.suryatradersadmin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatradersadmin.databinding.CustomerOrderDetailRowBinding;
import com.example.suryatradersadmin.databinding.EmployeeRowBinding;
import com.example.suryatradersadmin.model.CustomerOrderDetail;
import com.example.suryatradersadmin.model.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerOrderDetailListAdapter extends ListAdapter<CustomerOrderDetail,CustomerOrderDetailListAdapter.CustomerOrderDetailViewHolder> {

    private static final String TAG="CustomerOrderDetailListAdapter ";

    public CustomerOrderDetailListAdapter()
    {
        super(CustomerOrderDetail.itemCallback);
    }

    @NonNull
    @Override
    public CustomerOrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        CustomerOrderDetailRowBinding customerOrderDetailRowBinding=CustomerOrderDetailRowBinding.inflate(layoutInflater, parent,false);
        return new CustomerOrderDetailViewHolder(customerOrderDetailRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderDetailViewHolder holder, int position) {
        CustomerOrderDetail customerOrderDetail =getItem(position);
        holder.customerOrderDetailRowBinding.setCustomerOrderDetail(customerOrderDetail);

    }

    public class CustomerOrderDetailViewHolder extends RecyclerView.ViewHolder {

        CustomerOrderDetailRowBinding customerOrderDetailRowBinding;

        public CustomerOrderDetailViewHolder(CustomerOrderDetailRowBinding binding)
        {
            super(binding.getRoot());
            this.customerOrderDetailRowBinding=binding;
        }


    }

}


