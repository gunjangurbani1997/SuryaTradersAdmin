package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitReviewResult {

    @SerializedName("msg")
    @Expose
    private String submitReviewMsg;

    public String getSubmitReviewMsg() {
        return submitReviewMsg;
    }

    public void setSubmitReviewMsg(String submitReviewMsg) {
        this.submitReviewMsg = submitReviewMsg;
    }
}
