package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApproveCustomerResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String approveCustomerStatus;
    @SerializedName("result")
    @Expose
    private ApproveCustomerResult approveCustomerResult;

    public String getApproveCustomerStatus() {
        return approveCustomerStatus;
    }

    public void setApproveCustomerStatus(String approveCustomerStatus) {
        this.approveCustomerStatus = approveCustomerStatus;
    }

    public ApproveCustomerResult getApproveCustomerResult() {
        return approveCustomerResult;
    }

    public void setApproveCustomerResult(ApproveCustomerResult approveCustomerResult) {
        this.approveCustomerResult = approveCustomerResult;
    }
}
