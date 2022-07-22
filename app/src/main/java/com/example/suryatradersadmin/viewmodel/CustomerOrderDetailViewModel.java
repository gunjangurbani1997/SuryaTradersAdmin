package com.example.suryatradersadmin.viewmodel;

import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.model.CustomerOrderDetail;
import com.example.suryatradersadmin.repository.CustomerRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CustomerOrderDetailViewModel extends ViewModel {

    CustomerRepository customerRepository=new CustomerRepository();
    public LiveData<List<CustomerOrderDetail>> getCustomerOrderDetailList(Integer customerId)
    {
        return customerRepository.getCustomerOrderDetailList(customerId);
    }
}
