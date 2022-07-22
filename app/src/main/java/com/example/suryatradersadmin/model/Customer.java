package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class Customer implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer customerId;
    @SerializedName("first_name")
    @Expose
    private String customerFirstName;
    @SerializedName("last_name")
    @Expose
    private String customerLastName;
    @SerializedName("mobile")
    @Expose
    private String customerMobileNo;
    @SerializedName("company")
    @Expose
    private String customerCompany;
    @SerializedName("admin_approved")
    @Expose
    private Integer isAdminApproved;
    @SerializedName("category")
    @Expose
    private String customerCategory;
    @SerializedName("address")
    @Expose
    private String customerAddress;

    private List<CustomerOrderDetail> customerOrderDetailList;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public List<CustomerOrderDetail> getCustomerOrderDetailList() {
        return customerOrderDetailList;
    }

    public void setCustomerOrderDetailList(List<CustomerOrderDetail> customerOrderDetailList) {
        this.customerOrderDetailList = customerOrderDetailList;
    }

    public Integer getIsAdminApproved() {
        return isAdminApproved;
    }

    public void setIsAdminApproved(Integer isAdminApproved) {
        this.isAdminApproved = isAdminApproved;
    }

    public String getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Customer(Integer customerId, String customerFirstName, String customerLastName, String customerMobileNo, String customerCompany, List<CustomerOrderDetail> customerOrderDetailList, Integer isAdminApproved, String customerCategory) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerMobileNo = customerMobileNo;
        this.customerCompany = customerCompany;
        this.customerCategory=customerCategory;
        this.isAdminApproved=isAdminApproved;
        this.customerOrderDetailList = customerOrderDetailList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerMobileNo='" + customerMobileNo + '\'' +
                ", customerCompany='" + customerCompany + '\'' +
                ", customerCategory='" + customerCategory + '\'' +
                ", isAdminApproved='" + isAdminApproved + '\'' +
                ", customerOrderDetailList=" + customerOrderDetailList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId) &&
                customerFirstName.equals(customer.customerFirstName) &&
                customerLastName.equals(customer.customerLastName) &&
                customerMobileNo.equals(customer.customerMobileNo) &&
                customerCategory.equals(customer.customerCategory)&&
                isAdminApproved.equals(customer.isAdminApproved)&&
                customerCompany.equals(customer.customerCompany) ;
    }



    public static DiffUtil.ItemCallback<Customer> itemCallback= new DiffUtil.ItemCallback<Customer>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull Customer oldItem, @androidx.annotation.NonNull Customer newItem) {
            return oldItem.getCustomerId().equals(newItem.getCustomerId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull Customer oldItem, @androidx.annotation.NonNull Customer newItem) {
            return oldItem.equals(newItem);
        }
    };


}
