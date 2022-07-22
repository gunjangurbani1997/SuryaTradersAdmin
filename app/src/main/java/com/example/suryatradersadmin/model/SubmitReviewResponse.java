package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitReviewResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private com.example.suryatradersadmin.model.SubmitReviewResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public com.example.suryatradersadmin.model.SubmitReviewResult getResult() {
        return result;
    }

    public void setResult(com.example.suryatradersadmin.model.SubmitReviewResult result) {
        this.result = result;
    }
}
