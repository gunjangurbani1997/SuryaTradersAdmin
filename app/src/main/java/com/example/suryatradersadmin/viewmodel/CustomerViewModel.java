package com.example.suryatradersadmin.viewmodel;

import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.repository.AdminRepository;
import com.example.suryatradersadmin.repository.CustomerRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomerViewModel extends ViewModel {

    CustomerRepository customerRepository = new CustomerRepository();
    MutableLiveData<Integer> customerOrderDetailMutableLiveData=new MutableLiveData<>();

    MutableLiveData<String> mChangeStatusMsg=new MutableLiveData<>();

    public  LiveData<String> getChangeStatusMsg() {
        return mChangeStatusMsg;
    }


    public LiveData<List<Customer>> getAllCustomers() {
        return customerRepository.getAllCustomer();
    }

    public void setCustomerOrderDetailList(Integer customerId)
    {
        customerOrderDetailMutableLiveData.setValue(customerId);
    }

    public void deleteCustomer(Integer customerId)
    {
        customerRepository.deleteCustomer(customerId,mChangeStatusMsg);
    }

    public void approveCustomer(String mobile,String customerCategory)
    {
        customerRepository.approveCustomer(mobile,customerCategory, mChangeStatusMsg);
    }


}
