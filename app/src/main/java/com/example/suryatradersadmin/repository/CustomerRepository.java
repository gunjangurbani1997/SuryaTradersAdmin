package com.example.suryatradersadmin.repository;

import android.util.Log;

import com.example.suryatradersadmin.api.RetroFitClient;

import com.example.suryatradersadmin.model.ApproveCustomerResponse;
import com.example.suryatradersadmin.model.ApproveCustomerResult;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.Customer;
import com.example.suryatradersadmin.model.CustomerOrderDetail;
import com.example.suryatradersadmin.model.CustomerOrderDetailResponse;
import com.example.suryatradersadmin.model.CustomerOrderDetailResult;
import com.example.suryatradersadmin.model.CustomerResponse;
import com.example.suryatradersadmin.model.CustomerResult;
import com.example.suryatradersadmin.model.DeleteCustomerResponse;
import com.example.suryatradersadmin.model.DeleteCustomerResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    private MutableLiveData<List<Customer>> mutableCustomerList;
    private MutableLiveData<List<CustomerOrderDetail>> mutableCustomerOrderDetailList;
    private static final String TAG = "CustomerRepository";
    public MutableLiveData<CSRFToken> CSRF_TOKEN=null;
    String csrfToken;



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





    public LiveData<List<Customer>> getAllCustomer()
    {
      /*  if (mutableCustomerList==null)
        {*/
            mutableCustomerList=new MutableLiveData<>();
            loadCustomerList();
     /*   }*/
        return mutableCustomerList;
    }

    public LiveData<List<CustomerOrderDetail>> getCustomerOrderDetailList(Integer customerId)
    {

            mutableCustomerOrderDetailList=new MutableLiveData<>();
            loadCustomerOrderDetailList(customerId);

            return mutableCustomerOrderDetailList;
    }

    private void loadCustomerOrderDetailList(Integer customerId) {

        Call<CustomerOrderDetailResponse> call= RetroFitClient.getInstance().getApi().getAllOrdersOfCustomer(customerId.toString());
        call.enqueue(new Callback<CustomerOrderDetailResponse>() {

            @Override
            public void onResponse(Call<CustomerOrderDetailResponse> call, Response<CustomerOrderDetailResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"Customer List ");
                    System.out.println(response.body());

                    CustomerOrderDetailResponse customerOrderDetailResponse=response.body();
                    CustomerOrderDetailResult customerOrderDetailResult=customerOrderDetailResponse.getCustomerOrderDetailResponseResult();
                    List<CustomerOrderDetail> customerOrderDetailList=customerOrderDetailResult.getCustomerOrderDetailList();

                    Log.d(TAG,"Id "+  customerOrderDetailList.get(0).getOrderId()+" "+"Name "+  customerOrderDetailList.get(0).getStatus());
                    mutableCustomerOrderDetailList.setValue(customerOrderDetailList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch customer list from server "+response.body().getCustomerOrderDetailResponseStatus());
                }
            }

            @Override
            public void onFailure(Call<CustomerOrderDetailResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to customer list from server");
            }
        });

    }


    private void loadCustomerList()
    {
        Call<CustomerResponse> call= RetroFitClient.getInstance().getApi().getAllCustomer();
        call.enqueue(new Callback<CustomerResponse>() {

            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"Customer List ");
                    System.out.println(response.body());

                    CustomerResponse customerResponse=response.body();
                    CustomerResult customerResult=customerResponse.getCustomerResponseResult();
                    List<Customer> customerList=customerResult.getCustomer();

                    Log.d(TAG,"Id "+  customerList.get(0).getCustomerId()+" "+"Name "+  customerList.get(0).getCustomerId());
                    mutableCustomerList.setValue(customerList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch customer list from server "+response.body().getCustomerResponseStatus());
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to  admin list from server");
            }
        });

    }


    public void deleteCustomer(final Integer customerId,final MutableLiveData<String> mChangeStatusMsg)
    {
        Call<DeleteCustomerResponse> call= RetroFitClient.getInstance().getApi().deleteCustomer(customerId);
        call.enqueue(new Callback<DeleteCustomerResponse>() {

            @Override
            public void onResponse(Call<DeleteCustomerResponse> call, Response<DeleteCustomerResponse> response) {

                if (response.isSuccessful())
                {
                    DeleteCustomerResponse deleteCustomerResponse=response.body();
                    DeleteCustomerResult deleteCustomerResult=deleteCustomerResponse.getDeleteCustomerResult();
                    String deleteCustomer=deleteCustomerResult.getDeleteCustomer();
                    mChangeStatusMsg.setValue(deleteCustomer+" "+response.body().getDeleteCustomerStatus());
                    loadCustomerList();
//                    mutableEmployeeType.setValue(employeeType);
                    Log.d(TAG, "onSuccess: reject customer success "+response.body().getDeleteCustomerStatus()+" "+ deleteCustomer);
                }
                else
                {
                    Log.d(TAG, "onFailure: reject customer "+response.body().getDeleteCustomerStatus());
                }
            }

            @Override
            public void onFailure(Call<DeleteCustomerResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: reject customer ");
            }
        });
    }

    public void approveCustomer(final String mobile,final String customerCategory, final MutableLiveData<String> mChangeStatusMsg)
    {
        Call<ApproveCustomerResponse> call= RetroFitClient.getInstance().getApi().approveCustomer(mobile,customerCategory);
        call.enqueue(new Callback<ApproveCustomerResponse>() {

            @Override
            public void onResponse(Call<ApproveCustomerResponse> call, Response<ApproveCustomerResponse> response) {

                if (response.isSuccessful())
                {
                    ApproveCustomerResponse approveCustomerResponse=response.body();
                    ApproveCustomerResult approveCustomerResult=approveCustomerResponse.getApproveCustomerResult();
                    String approveCustomer=approveCustomerResult.getApproveCustomer();
                    mChangeStatusMsg.setValue(approveCustomer+" "+response.body().getApproveCustomerStatus());
                    loadCustomerList();
//                    mutableEmployeeType.setValue(employeeType);
                    Log.d(TAG, "onSuccess: approve customer success "+response.body().getApproveCustomerStatus()+" "+ approveCustomer);
                }
                else
                {
                    Log.d(TAG, "onFailure: approve customer "+response.body().getApproveCustomerStatus());
                }
            }

            @Override
            public void onFailure(Call<ApproveCustomerResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: approve customer ");
            }
        });
    }
}
