package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmployeeResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer employeeResponseStatus;
    @SerializedName("result")
    @Expose
    private EmployeeResult employeeResponseResult;

    public Integer getEmployeeResponseStatus() {
        return employeeResponseStatus;
    }

    public void setEmployeeResponseStatus(Integer employeeResponseStatus) {
        this.employeeResponseStatus = employeeResponseStatus;
    }

    public EmployeeResult getEmployeeResponseResult() {
        return employeeResponseResult;
    }

    public void setEmployeeResponseResult(EmployeeResult employeeResponseResult) {
        this.employeeResponseResult = employeeResponseResult;
    }
}
