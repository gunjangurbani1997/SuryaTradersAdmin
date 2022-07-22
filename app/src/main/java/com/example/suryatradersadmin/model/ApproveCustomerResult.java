package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApproveCustomerResult {

    @SerializedName("msg")
    @Expose
    private String approveCustomer;

    public String getApproveCustomer() {
        return approveCustomer;
    }

    public void setApproveCustomer(String approveCustomer) {
        this.approveCustomer = approveCustomer;
    }
}
