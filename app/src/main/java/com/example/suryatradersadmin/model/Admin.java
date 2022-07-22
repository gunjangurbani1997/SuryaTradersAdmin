package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class Admin implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer adminId;
    @SerializedName("first_name")
    @Expose
    private String adminFirstName;
    @SerializedName("last_name")
    @Expose
    private String adminLastName;
    @SerializedName("mobile")
    @Expose
    private String adminPhoneNo;
    @SerializedName("password")
    @Expose
    private String adminPassword;


    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminPhoneNo() {
        return adminPhoneNo;
    }

    public void setAdminPhoneNo(String adminPhoneNo) {
        this.adminPhoneNo = adminPhoneNo;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin(Integer adminId, String adminFirstName, String adminLastName, String adminPhoneNo, String adminPassword) {
        this.adminId = adminId;
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminPhoneNo = adminPhoneNo;
        this.adminPassword=adminPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return adminId.equals(admin.adminId) &&
                adminFirstName.equals(admin.adminFirstName) &&
                adminLastName.equals(admin.adminLastName) &&
                adminPhoneNo.equals(admin.adminPhoneNo) &&
                adminPassword.equals(admin.adminPassword);

    }

    public static DiffUtil.ItemCallback<Admin> itemCallback= new DiffUtil.ItemCallback<Admin>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull Admin oldItem, @androidx.annotation.NonNull Admin newItem) {
            return oldItem.getAdminId().equals(newItem.getAdminId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull Admin oldItem, @androidx.annotation.NonNull Admin newItem) {
            return oldItem.equals(newItem);
        }
    };
}
