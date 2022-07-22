package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReviewResult implements Serializable {

    @SerializedName("msg")
    @Expose
    private List<com.example.suryatradersadmin.model.Review> review = null;

    public List<com.example.suryatradersadmin.model.Review> getReview() {
        return review;
    }

    public void setReview(List<com.example.suryatradersadmin.model.Review> review) {
        this.review = review;
    }
}
