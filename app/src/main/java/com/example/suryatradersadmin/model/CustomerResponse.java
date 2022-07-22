package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String customerResponseStatus;
    @SerializedName("result")
    @Expose
    private CustomerResult customerResponseResult;

    public String getCustomerResponseStatus() {
        return customerResponseStatus;
    }

    public void setCustomerResponseStatus(String customerResponseStatus) {
        this.customerResponseStatus = customerResponseStatus;
    }

    public CustomerResult getCustomerResponseResult() {
        return customerResponseResult;
    }

    public void setCustomerResponseResult(CustomerResult customerResponseResult) {
        this.customerResponseResult = customerResponseResult;
    }
}
