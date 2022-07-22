package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubOrderResponse {
    @SerializedName("status")
    @Expose
    private String subOrderResponseStatus;
    @SerializedName("result")
    @Expose
    private SubOrderResult subOrderResponseResult;

    public String getSubOrderResponseStatus() {
        return subOrderResponseStatus;
    }

    public void setSubOrderResponseStatus(String subOrderResponseStatus) {
        this.subOrderResponseStatus = subOrderResponseStatus;
    }

    public SubOrderResult getSubOrderResponseResult() {
        return subOrderResponseResult;
    }

    public void setSubOrderResponseResult(SubOrderResult subOrderResponseResult) {
        this.subOrderResponseResult = subOrderResponseResult;
    }
}
