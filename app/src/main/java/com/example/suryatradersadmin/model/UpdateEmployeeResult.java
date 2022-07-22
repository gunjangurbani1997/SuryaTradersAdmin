package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateEmployeeResult {

    @SerializedName("msg")
    @Expose
    private String updateEmployeeType;

    public String getUpdateEmployeeType() {
        return updateEmployeeType;
    }

    public void setUpdateEmployeeType(String updateEmployeeType) {
        this.updateEmployeeType = updateEmployeeType;
    }
}
