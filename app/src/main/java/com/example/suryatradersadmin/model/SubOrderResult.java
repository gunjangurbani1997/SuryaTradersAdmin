package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubOrderResult {

    @SerializedName("msg")
    @Expose
    private List<SubOrders> subOrdersList;

    public List<SubOrders> getSubOrdersList() {
        return subOrdersList;
    }

    public void setSubOrdersList(List<SubOrders> subOrdersList) {
        this.subOrdersList = subOrdersList;
    }
}
