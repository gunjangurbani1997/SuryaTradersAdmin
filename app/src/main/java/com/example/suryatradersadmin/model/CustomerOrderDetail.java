package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class CustomerOrderDetail implements Serializable {

    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdOn;
    @SerializedName("updated_at")
    @Expose
    private String updatedOn;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CustomerOrderDetail(Integer orderId, String status, String  createdOn, String updatedOn) {
        this.orderId = orderId;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrderDetail that = (CustomerOrderDetail) o;
        return orderId.equals(that.orderId) &&
                status.equals(that.status) &&
                createdOn.equals(that.createdOn) &&
                updatedOn.equals(that.updatedOn);
    }


    public static DiffUtil.ItemCallback<CustomerOrderDetail> itemCallback= new DiffUtil.ItemCallback<CustomerOrderDetail>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull CustomerOrderDetail oldItem, @androidx.annotation.NonNull CustomerOrderDetail newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull CustomerOrderDetail oldItem, @androidx.annotation.NonNull CustomerOrderDetail newItem) {
            return oldItem.equals(newItem);
        }
    };

}
