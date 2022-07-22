package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {

    @SerializedName("status")
    @Expose
    private String orderResponseStatus;
    @SerializedName("result")
    @Expose
    private OrderResult orderResponseResult;


    public String getOrderResponseStatus() {
        return orderResponseStatus;
    }

    public void setOrderResponseStatus(String orderResponseStatus) {
        this.orderResponseStatus = orderResponseStatus;
    }

    public OrderResult getOrderResponseResult() {
        return orderResponseResult;
    }

    public void setOrderResponseResult(OrderResult orderResponseResult) {
        this.orderResponseResult = orderResponseResult;
    }
}
