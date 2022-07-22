package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateEmployeeResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Integer updateEmployeeStatus;
    @SerializedName("result")
    @Expose
    private UpdateEmployeeResult updateEmployeeResult;

    public Integer getUpdateEmployeeStatus() {
        return updateEmployeeStatus;
    }

    public void setUpdateEmployeeStatus(Integer updateEmployeeStatus) {
        this.updateEmployeeStatus = updateEmployeeStatus;
    }

    public UpdateEmployeeResult getUpdateEmployeeResult() {
        return updateEmployeeResult;
    }

    public void setUpdateEmployeeResult(UpdateEmployeeResult updateEmployeeResult) {
        this.updateEmployeeResult = updateEmployeeResult;
    }
}
