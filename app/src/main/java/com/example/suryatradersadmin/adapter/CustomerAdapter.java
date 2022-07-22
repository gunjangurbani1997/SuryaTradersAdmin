package com.example.suryatradersadmin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatradersadmin.databinding.AdminRowBinding;
import com.example.suryatradersadmin.databinding.CustomerRowBinding;
import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.model.Employee;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerAdapter extends ListAdapter<Customer, CustomerAdapter.CustomerViewHolder> {


    private static final String TAG="CustomerAdapter ";
    CustomerOrderDetailInterface customerOrderDetailInterface;

    public CustomerAdapter(CustomerOrderDetailInterface customerOrderDetailInterface)
    {
        super(Customer.itemCallback);
        this.customerOrderDetailInterface=customerOrderDetailInterface;


    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        CustomerRowBinding customerRowBinding=CustomerRowBinding.inflate(layoutInflater, parent,false);
        customerRowBinding.setCustomerOrderDetailInterface(customerOrderDetailInterface);
        return new CustomerViewHolder(customerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer =getItem(position);
        holder.customerRowBinding.setCustomer(customer);
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        CustomerRowBinding customerRowBinding;

        public CustomerViewHolder(CustomerRowBinding binding)
        {
            super(binding.getRoot());
            this.customerRowBinding=binding;

        }
    }

    public interface CustomerOrderDetailInterface
    {
        public void onCustomerClick(Integer customerId);
        public void deleteCustomer(Integer customerId);
        public void approveCustomer(String mobile,String customerCategory);
    }
}
