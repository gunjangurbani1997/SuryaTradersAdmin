package com.example.suryatradersadmin.viewmodel;

import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.repository.EmployeeRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmployeeViewModel extends ViewModel {

    EmployeeRepository employeeRepository=new EmployeeRepository();
    MutableLiveData<String> mChangeStatusMsg=new MutableLiveData<>();

    public  LiveData<String> getChangeStatusMsg() {
        return mChangeStatusMsg;
    }

    public LiveData<List<Employee>> getAllEmployees()
    {
        return employeeRepository.getAllEmployees();
    }

    public void updateEmployeeType(String employeeType,String employeePhoneNo)
    {
      employeeRepository.updateEmployeeType(employeeType,employeePhoneNo,mChangeStatusMsg);
    }




}
