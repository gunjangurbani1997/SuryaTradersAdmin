package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerOrderDetailResponse {

    @SerializedName("status")
    @Expose
    private String customerOrderDetailResponseStatus;
    @SerializedName("result")
    @Expose
    private CustomerOrderDetailResult customerOrderDetailResponseResult;

    public String getCustomerOrderDetailResponseStatus() {
        return customerOrderDetailResponseStatus;
    }

    public void setCustomerOrderDetailResponseStatus(String customerOrderDetailResponseStatus) {
        this.customerOrderDetailResponseStatus = customerOrderDetailResponseStatus;
    }

    public CustomerOrderDetailResult getCustomerOrderDetailResponseResult() {
        return customerOrderDetailResponseResult;
    }

    public void setCustomerOrderDetailResponseResult(CustomerOrderDetailResult customerOrderDetailResponseResult) {
        this.customerOrderDetailResponseResult = customerOrderDetailResponseResult;
    }
}
