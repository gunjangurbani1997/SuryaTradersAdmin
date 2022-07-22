package com.example.suryatradersadmin.viewmodel;

import com.example.suryatradersadmin.model.CustomerOrderDetail;
import com.example.suryatradersadmin.model.SubOrders;
import com.example.suryatradersadmin.repository.CustomerRepository;
import com.example.suryatradersadmin.repository.SubOrderRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SubOrderViewModel extends ViewModel {

    SubOrderRepository subOrderRepository=new SubOrderRepository();
    MutableLiveData<String> mChangeStatusMsg=new MutableLiveData<>();
    public LiveData<List<SubOrders>> getCustomerOrderDetailList(Integer customerId,Integer orderId)
    {
      return subOrderRepository.getSubOrderList(customerId,orderId);
    }

    public void assignEmployee(Integer orderId)
    {
        subOrderRepository.assignEmployee(orderId,mChangeStatusMsg);
    }

    public  LiveData<String> getChangeStatusMsg() {
        return mChangeStatusMsg;
    }
}
