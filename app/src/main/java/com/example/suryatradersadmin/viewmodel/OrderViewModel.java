package com.example.suryatradersadmin.viewmodel;

import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.model.Orders;
import com.example.suryatradersadmin.repository.OrderRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {


    MutableLiveData<Integer> customerOrderListLiveData=new MutableLiveData<>();
    MutableLiveData<Integer> subOrderListLiveData=new MutableLiveData<>();
    MutableLiveData<String> mChangeStatusMsg=new MutableLiveData<>();
    MutableLiveData<Integer> reviewOrderListLiveData=new MutableLiveData<>();


    OrderRepository orderRepository=new OrderRepository();

    public LiveData<List<Orders>> getAllOrders()
    {
        return orderRepository.getAllOrders();
    }

    public void setSubOrderList(Integer customerId,Integer orderId)
    {
        subOrderListLiveData.setValue(customerId);
        subOrderListLiveData.setValue(orderId);
    }

    public void changeStatus(Integer orderId,String orderStatus)
    {
        orderRepository.changeStatus(orderId,orderStatus,mChangeStatusMsg);
    }

    public void assignEmployee(Integer orderId)
    {
        orderRepository.assignEmployee(orderId,mChangeStatusMsg);
    }

    public void deleteOrder(Integer orderId)
    {
        orderRepository.deleteOrder(orderId, mChangeStatusMsg);
    }


    public  LiveData<String> getChangeStatusMsg() {
        return mChangeStatusMsg;
    }

    public void setReviewList(Integer orderId)
    {
        reviewOrderListLiveData.setValue(orderId);
    }
}
