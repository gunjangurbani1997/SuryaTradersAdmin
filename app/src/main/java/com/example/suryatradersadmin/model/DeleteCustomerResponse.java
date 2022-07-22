package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteCustomerResponse {

    @SerializedName("status")
    @Expose
    private String deleteCustomerStatus;
    @SerializedName("result")
    @Expose
    private DeleteCustomerResult deleteCustomerResult;

    public String getDeleteCustomerStatus() {
        return deleteCustomerStatus;
    }

    public void setDeleteCustomerStatus(String deleteCustomerStatus) {
        this.deleteCustomerStatus = deleteCustomerStatus;
    }

    public DeleteCustomerResult getDeleteCustomerResult() {
        return deleteCustomerResult;
    }

    public void setDeleteCustomerResult(DeleteCustomerResult deleteCustomerResult) {
        this.deleteCustomerResult = deleteCustomerResult;
    }
}
