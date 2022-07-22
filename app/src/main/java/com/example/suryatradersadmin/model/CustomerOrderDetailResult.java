package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerOrderDetailResult {

    @SerializedName("msg")
    @Expose
    private List<CustomerOrderDetail> customerOrderDetailList;

    public List<CustomerOrderDetail> getCustomerOrderDetailList() {
        return customerOrderDetailList;
    }

    public void setCustomerOrderDetailList(List<CustomerOrderDetail> customerOrderDetailList) {
        this.customerOrderDetailList = customerOrderDetailList;
    }
}
