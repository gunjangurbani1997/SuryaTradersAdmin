package com.example.suryatradersadmin.repository;

import android.util.Log;

import com.example.suryatradersadmin.api.RetroFitClient;
import com.example.suryatradersadmin.model.ApproveCustomerResponse;
import com.example.suryatradersadmin.model.ApproveCustomerResult;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.SubOrderResponse;
import com.example.suryatradersadmin.model.SubOrderResult;
import com.example.suryatradersadmin.model.SubOrders;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubOrderRepository {

    private MutableLiveData<List<SubOrders>> mutableSubOrderList;

    private static final String TAG = "SubOrderRepository ";
    public MutableLiveData<CSRFToken> CSRF_TOKEN=new MutableLiveData<>();


    public LiveData<List<SubOrders>> getSubOrderList(Integer customerId,Integer orderId)
    {

            mutableSubOrderList=new MutableLiveData<>();
            getCSRFToken();
            loadSubOrderList(customerId,orderId);
        
        return mutableSubOrderList;
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


    private void loadSubOrderList(Integer customerId,Integer orderId) {


        Call<SubOrderResponse> call= RetroFitClient.getInstance().getApi().getSubOrderList(customerId.toString(),orderId.toString());
        call.enqueue(new Callback<SubOrderResponse>() {

            @Override
            public void onResponse(Call<SubOrderResponse> call, Response<SubOrderResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"SubOrder List ");
                    System.out.println(response.body());

                    SubOrderResponse subOrderResponse=response.body();
                    SubOrderResult subOrderResult=subOrderResponse.getSubOrderResponseResult();
                    List<SubOrders> subOrdersList=subOrderResult.getSubOrdersList();

                    Log.d(TAG,"Id "+  subOrdersList.get(0).getOrderId()+" "+"Name "+  subOrdersList.get(0).getProductName());
                    mutableSubOrderList.setValue(subOrdersList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch order list from server"+response.code());
                }
            }

            @Override
            public void onFailure(Call<SubOrderResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to  suborder list from server");
            }
        });

    }

    public void assignEmployee(final Integer orderId, final MutableLiveData<String> mChangeStatusMsg)
    {

        Call<ApproveCustomerResponse> call= RetroFitClient.getInstance().getApi().assignEmployee(orderId);
        call.enqueue(new Callback<ApproveCustomerResponse>() {

            @Override
            public void onResponse(Call<ApproveCustomerResponse> call, Response<ApproveCustomerResponse> response) {

                if (response.isSuccessful())
                {
                    ApproveCustomerResponse approveCustomerResponse=response.body();
                    ApproveCustomerResult approveCustomerResult=approveCustomerResponse.getApproveCustomerResult();
                    String approveCustomer=approveCustomerResult.getApproveCustomer();
                    mChangeStatusMsg.setValue(approveCustomer);

//                    mutableEmployeeType.setValue(employeeType);
                    Log.d(TAG, "onSuccess: update order status success "+response.body().getApproveCustomerStatus()+" "+ approveCustomer);
                }
                else
                {
                    Log.d(TAG, "onFailure: update order status "+response.body().getApproveCustomerStatus());
                }
            }

            @Override
            public void onFailure(Call<ApproveCustomerResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: update order status ");
            }
        });
    }
}
