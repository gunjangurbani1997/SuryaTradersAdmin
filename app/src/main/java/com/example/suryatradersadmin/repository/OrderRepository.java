package com.example.suryatradersadmin.repository;

import android.util.Log;

import com.example.suryatradersadmin.api.RetroFitClient;
import com.example.suryatradersadmin.model.ApproveCustomerResponse;
import com.example.suryatradersadmin.model.ApproveCustomerResult;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.DeleteCustomerResponse;
import com.example.suryatradersadmin.model.DeleteCustomerResult;
import com.example.suryatradersadmin.model.OrderResponse;
import com.example.suryatradersadmin.model.OrderResult;
import com.example.suryatradersadmin.model.Orders;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {

    private MutableLiveData<List<Orders>> mutableOrdersList;
    public MutableLiveData<CSRFToken> CSRF_TOKEN;

    private static final String TAG = "OrderRepository ";
    String csrfToken;

    public LiveData<List<Orders>> getAllOrders()
    {
      /*  if (mutableOrdersList==null)
        {*/
            mutableOrdersList=new MutableLiveData<>();
            loadOrdersList();
            getCSRFToken();
     /*   }*/
        return mutableOrdersList;
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

    private void loadOrdersList() {

        Call<OrderResponse> call= RetroFitClient.getInstance().getApi().getAllOrders();
        call.enqueue(new Callback<OrderResponse>() {

            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"Order List ");
                    System.out.println(response.body());

                    OrderResponse orderResponse=response.body();
                    OrderResult orderResult=orderResponse.getOrderResponseResult();
                    List<Orders> orderList=orderResult.getOrderList();

                    Log.d(TAG,"Id "+  orderList.get(0).getOrderId()+" "+"Name "+  orderList.get(0).getCustomerId());
                    mutableOrdersList.setValue(orderList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch order list from server"+response.code());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to fetch order list from server");
            }
        });
    }

    public void changeStatus(Integer orderId, String orderStatus, final MutableLiveData<String> mChangeStatusMsg)
    {

        Call<ApproveCustomerResponse> call= RetroFitClient.getInstance().getApi().changeOrderStatus(orderId,orderStatus);
        call.enqueue(new Callback<ApproveCustomerResponse>() {

            @Override
            public void onResponse(Call<ApproveCustomerResponse> call, Response<ApproveCustomerResponse> response) {

                if (response.isSuccessful())
                {
                    ApproveCustomerResponse approveCustomerResponse=response.body();
                    ApproveCustomerResult approveCustomerResult=approveCustomerResponse.getApproveCustomerResult();
                    String approveCustomer=approveCustomerResult.getApproveCustomer();
                    mChangeStatusMsg.setValue(approveCustomer);
                    loadOrdersList();
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

    public void assignEmployee(Integer orderId, final MutableLiveData<String> mChangeStatusMsg)
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
                    loadOrdersList();
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



    public void deleteOrder(final Integer orderId, final MutableLiveData<String> mChangeStatusMsg)
    {
        Call<DeleteCustomerResponse> call= RetroFitClient.getInstance().getApi().deleteOrder(orderId);
        call.enqueue(new Callback<DeleteCustomerResponse>() {

            @Override
            public void onResponse(Call<DeleteCustomerResponse> call, Response<DeleteCustomerResponse> response) {

                if (response.isSuccessful())
                {
                    DeleteCustomerResponse deleteCustomerResponse=response.body();
                    DeleteCustomerResult deleteCustomerResult=deleteCustomerResponse.getDeleteCustomerResult();
                    String deleteCustomer=deleteCustomerResult.getDeleteCustomer();
                    mChangeStatusMsg.setValue(deleteCustomer+" "+response.body().getDeleteCustomerStatus());
                    loadOrdersList();
//                    mutableEmployeeType.setValue(employeeType);
                    Log.d(TAG, "onSuccess: delete order success "+response.body().getDeleteCustomerStatus()+" "+ deleteCustomer);
                }
                else
                {
                    Log.d(TAG, "onFailure: delete order "+response.body().getDeleteCustomerStatus());
                }
            }

            @Override
            public void onFailure(Call<DeleteCustomerResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: delete order ");
            }
        });
    }
}
