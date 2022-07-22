package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class Employee  implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer employeeId;
    @SerializedName("first_name")
    @Expose
    private String employeeFirstName;
    @SerializedName("last_name")
    @Expose
    private String employeeLastName;
    @SerializedName("mobile")
    @Expose
    private String employeePhoneNo;
    @SerializedName("type")
    @Expose
    private String employeeLevel;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeePhoneNo() {
        return employeePhoneNo;
    }

    public void setEmployeePhoneNo(String employeePhoneNo) {
        this.employeePhoneNo = employeePhoneNo;
    }

    public String getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(String employeeLevel) {
        this.employeeLevel = employeeLevel;
    }


    public static DiffUtil.ItemCallback<Employee> itemCallback= new DiffUtil.ItemCallback<Employee>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull Employee oldItem, @androidx.annotation.NonNull Employee newItem) {
            return oldItem.getEmployeeId().equals(newItem.getEmployeeId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull Employee oldItem, @androidx.annotation.NonNull Employee newItem) {
            return oldItem.equals(newItem);
        }
    };


    public Employee(Integer employeeId, String employeeFirstName,String employeeLastName, String employeePhoneNo, String employeeLevel) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeLastName;
        this.employeeLastName=employeeLastName;
        this.employeePhoneNo = employeePhoneNo;
        this.employeeLevel = employeeLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) &&
                employeeFirstName.equals(employee.employeeFirstName) &&
                employeeLastName.equals(employee.employeeLastName) &&
                employeePhoneNo.equals(employee.employeePhoneNo) &&
                employeeLevel.equals(employee.employeeLevel);
    }


}
