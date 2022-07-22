package com.example.suryatradersadmin.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatradersadmin.R;
import com.example.suryatradersadmin.adapter.CustomerOrderDetailListAdapter;
import com.example.suryatradersadmin.databinding.FragmentCustomerOrderDetailBinding;
import com.example.suryatradersadmin.model.CustomerOrderDetail;
import com.example.suryatradersadmin.viewmodel.CustomerOrderDetailViewModel;

import java.util.List;


public class CustomerOrderDetailFragment extends Fragment {

    FragmentCustomerOrderDetailBinding fragmentCustomerOrderDetailBinding;
    CustomerOrderDetailListAdapter customerOrderDetailListAdapter;
    CustomerOrderDetailViewModel customerOrderDetailViewModel;

    public CustomerOrderDetailFragment() {

    }


    public static CustomerOrderDetailFragment newInstance(String param1, String param2) {
        CustomerOrderDetailFragment fragment = new CustomerOrderDetailFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCustomerOrderDetailBinding=FragmentCustomerOrderDetailBinding.inflate(inflater,container,false);
        return fragmentCustomerOrderDetailBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer customerId = null;
        if (getArguments() != null) {

            CustomerOrderDetailFragmentArgs args = CustomerOrderDetailFragmentArgs.fromBundle(getArguments());
            customerId = args.getCustomerId();

        }

        customerOrderDetailListAdapter = new CustomerOrderDetailListAdapter();
        fragmentCustomerOrderDetailBinding.customerOrderDetailRecyclerview.setAdapter(customerOrderDetailListAdapter);
        customerOrderDetailViewModel=new ViewModelProvider(requireActivity()).get(CustomerOrderDetailViewModel.class);

        customerOrderDetailViewModel.getCustomerOrderDetailList(customerId).observe(getViewLifecycleOwner(), new Observer<List<CustomerOrderDetail>>() {
                    @Override
                    public void onChanged(List<CustomerOrderDetail> customerOrderDetails) {
                        customerOrderDetailListAdapter.submitList(customerOrderDetails);
                    }
                });

    }

}