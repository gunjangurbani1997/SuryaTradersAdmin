package com.example.suryatradersadmin.model;

import com.example.suryatradersadmin.viewmodel.OrderViewModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class Orders {

    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("display_status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sub_orders")
    @Expose
    private List<SubOrders> subOrderList;

    private List<String> statusList;

    private List<String> employeeList;



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<SubOrders> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrders> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<String> employeeList) {
        this.employeeList = employeeList;
    }

    public Orders(Integer orderId, Integer customerId, String status, String customerName, List<SubOrders> subOrderList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.customerName=customerName;
        this.subOrderList = subOrderList;
    }

    public Orders()
    {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return orderId.equals(orders.orderId) &&
                customerId.equals(orders.customerId) &&
                status.equals(orders.status) &&
                customerName.equals(orders.customerName) &&
                subOrderList.equals(orders.subOrderList);
    }

    public static DiffUtil.ItemCallback<Orders> itemCallback= new DiffUtil.ItemCallback<Orders>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull Orders oldItem, @androidx.annotation.NonNull Orders newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull Orders oldItem, @androidx.annotation.NonNull Orders newItem) {
            return oldItem.equals(newItem);
        }
    };
}
