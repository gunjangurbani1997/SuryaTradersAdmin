package com.example.suryatradersadmin.repository;

import android.util.Log;

import com.example.suryatradersadmin.api.RetroFitClient;
import com.example.suryatradersadmin.api.RetroFitClient;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.Employee;
import com.example.suryatradersadmin.model.EmployeeResponse;
import com.example.suryatradersadmin.model.EmployeeResult;
import com.example.suryatradersadmin.model.UpdateEmployeeResponse;
import com.example.suryatradersadmin.model.UpdateEmployeeResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private MutableLiveData<List<Employee>> mutableEmployeeList;
    private MutableLiveData<String> mutableEmployeeType;
    private static final String TAG = "EmployeeRepository";
    public MutableLiveData<CSRFToken> CSRF_TOKEN=new MutableLiveData<>();
   // public final MutableLiveData<String> mChangeStatusMsg=null;



    public LiveData<List<Employee>> getAllEmployees()
    {
      /*  if (mutableEmployeeList==null)
        {*/
            mutableEmployeeList=new MutableLiveData<>();
            getCSRFToken();
            loadEmployeeList();
      /*  }*/
        return mutableEmployeeList;
    }

    public LiveData<List<Employee>> getNewEmployeeType(String employeeType,String employeePhoneNo)
    {

        mutableEmployeeList=new MutableLiveData<>();
        mutableEmployeeType=new MutableLiveData<>();
      //  updateEmployeeType(employeeType,employeePhoneNo,mChangeStatusMsg);

        return mutableEmployeeList;
    }


    public LiveData<CSRFToken> getCSRFToken()
    {
       /* if (CSRF_TOKEN==null  )
        {*/
        CSRF_TOKEN=new MutableLiveData<>();
        CSRFToken();
        /*   }*/

        return CSRF_TOKEN;
    }

    public void CSRFToken()
    {
        Call<CSRFToken> csrfTokenCall= RetroFitClient.getInstance().getApi().getCsrfToken();
        csrfTokenCall.enqueue(new Callback<CSRFToken>() {

            @Override
            public void onResponse(Call<CSRFToken > call, Response<CSRFToken> response) {

                if (response.body()!=null) {

                    CSRFToken csrfToken=response.body();
                    CSRF_TOKEN.setValue(csrfToken);
                }
            }

            @Override
            public void onFailure(Call<CSRFToken> call, Throwable t)
            {
                android.util.Log.d(TAG, "onFailure:"+t.getCause());
                android.util.Log.d(TAG, "onFailure: failed to get CSRF Token");
            }

        });
    }



    private void loadEmployeeList()
    {

        Call<EmployeeResponse> call= RetroFitClient.getInstance().getApi().getAllEmployees();
        call.enqueue(new Callback<EmployeeResponse>() {

            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                if (response.body().getEmployeeResponseStatus()==200)
                {
                    Log.d(TAG,"Employee List ");
                    EmployeeResponse employeeResponse=response.body();
                    EmployeeResult employeeResult=employeeResponse.getEmployeeResponseResult();
                    List<Employee> employeeList=employeeResult.getEmployee();
                    mutableEmployeeList.setValue(employeeList);
                    Log.d(TAG, "onSuccess: employee list from server fetched "+response.body().getEmployeeResponseStatus());
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch employee list from server "+response.body().getEmployeeResponseStatus());
                }
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to  employee list from server");
            }
        });

    }
    public void updateEmployeeType(final String employeeType, String employeePhoneNo,final MutableLiveData<String> mChangeStatusMsg)
    {

        Log.d(TAG,"Csrf Token "+CSRF_TOKEN.getValue().getCsrf().toString());
        Call<UpdateEmployeeResponse> call= RetroFitClient.getInstance().getApi().updateEmployeeType(employeeType,employeePhoneNo);
        call.enqueue(new Callback<UpdateEmployeeResponse>() {

            @Override
            public void onResponse(Call<UpdateEmployeeResponse> call, Response<UpdateEmployeeResponse> response) {

                if (response.body().getUpdateEmployeeStatus()==200)
                {
                    UpdateEmployeeResponse updateEmployeeResponse=response.body();
                    UpdateEmployeeResult updateEmployeeResult=updateEmployeeResponse.getUpdateEmployeeResult();
                    String updateEmployeeType=updateEmployeeResult.getUpdateEmployeeType();
                    mChangeStatusMsg.setValue(updateEmployeeType+" "+response.body().getUpdateEmployeeStatus());
                    loadEmployeeList();
                    Log.d(TAG, "onSuccess: update employee type success "+response.body().getUpdateEmployeeStatus()+" "+ updateEmployeeType);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to update employee type "+response.body().getUpdateEmployeeStatus());
                }
            }

            @Override
            public void onFailure(Call<UpdateEmployeeResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to update employee type");
            }
        });
    }


}
