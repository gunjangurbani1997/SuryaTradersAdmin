package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteCustomerResult {

    @SerializedName("msg")
    @Expose
    private String deleteCustomer;

    public String getDeleteCustomer() {
        return deleteCustomer;
    }

    public void setDeleteCustomer(String deleteCustomer) {
        this.deleteCustomer = deleteCustomer;
    }
}
