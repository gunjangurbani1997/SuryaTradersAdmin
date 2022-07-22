package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.recyclerview.widget.DiffUtil;

public class Review implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer reviewId;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("added_by_type")
    @Expose
    private String addedByType;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("added_on")
    @Expose
    private  String addedOn;


    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddedByType() {
        return addedByType;
    }

    public void setAddedByType(String addedByType) {
        this.addedByType = addedByType;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.example.suryatradersadmin.model.Review review = (com.example.suryatradersadmin.model.Review) o;
        return reviewId.equals(review.reviewId) &&
                orderId.equals(review.orderId) &&
                remarks.equals(review.remarks) &&
                addedByType.equals(review.addedByType) &&
                addedBy.equals(review.addedBy) &&
                addedOn.equals(review.addedOn);
    }

    public static DiffUtil.ItemCallback<com.example.suryatradersadmin.model.Review> itemCallback= new DiffUtil.ItemCallback<com.example.suryatradersadmin.model.Review>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull com.example.suryatradersadmin.model.Review oldItem, @androidx.annotation.NonNull com.example.suryatradersadmin.model.Review newItem) {
            return oldItem.getReviewId().equals(newItem.getReviewId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull com.example.suryatradersadmin.model.Review oldItem, @androidx.annotation.NonNull com.example.suryatradersadmin.model.Review newItem) {
            return oldItem.equals(newItem);
        }
    };

}
