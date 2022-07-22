package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AdminResponse implements Serializable {


    @SerializedName("status")
    @Expose
    private String adminResponseStatus;
    @SerializedName("result")
    @Expose
    private AdminResult adminResponseResult;

    public String getAdminResponseStatus() {
        return adminResponseStatus;
    }

    public void setAdminResponseStatus(String adminResponseStatus) {
        this.adminResponseStatus = adminResponseStatus;
    }

    public AdminResult getAdminResponseResult() {
        return adminResponseResult;
    }

    public void setAdminResponseResult(AdminResult adminResponseResult) {
        this.adminResponseResult = adminResponseResult;
    }
}
