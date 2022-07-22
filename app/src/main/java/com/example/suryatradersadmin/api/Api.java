package com.example.suryatradersadmin.api;

import com.example.suryatradersadmin.model.AdminResponse;
import com.example.suryatradersadmin.model.ApproveCustomerResponse;
import com.example.suryatradersadmin.model.AuthenticationResponse;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.CustomerOrderDetailResponse;
import com.example.suryatradersadmin.model.CustomerResponse;
import com.example.suryatradersadmin.model.DeleteCustomerResponse;

import com.example.suryatradersadmin.model.EmployeeResponse;
import com.example.suryatradersadmin.model.OrderResponse;
import com.example.suryatradersadmin.model.ReviewMsg;
import com.example.suryatradersadmin.model.SubOrderResponse;
import com.example.suryatradersadmin.model.SubmitReviewResponse;
import com.example.suryatradersadmin.model.UpdateEmployeeResponse;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    @Headers("Content-Type:application/json;")
    @GET("employee/list?")
    Call<EmployeeResponse> getAllEmployees();

    @Headers("Content-Type:application/json;")
    @GET("admin/list?")
    Call<AdminResponse> getAllAdmin();

    @Headers("Content-Type:application/json;")
    @GET("customer/list?")
    Call<CustomerResponse> getAllCustomer();

    @GET("admin/order/get?")
    Call<CustomerOrderDetailResponse> getAllOrdersOfCustomer(@Query("customer_id") String customerId);

    @GET("admin/order/detail?")
    Call<SubOrderResponse> getSubOrderList(@Query("customer_id") String customerId, @Query("order_id") String orderId);

    @DELETE("customer/delete?")
    Call<DeleteCustomerResponse> deleteCustomer(@Query("customer_id") Integer customerId);

    @FormUrlEncoded
    @POST("customer/approve")
    Call<ApproveCustomerResponse> approveCustomer(@Field("mobile") String customerMobileNo ,@Field("category") String customerCategory);

    @FormUrlEncoded
    @POST("admin/order/update/status")
    Call<ApproveCustomerResponse> changeOrderStatus(@Field("order_id") Integer orderId ,@Field("status") String orderStatus);

    @FormUrlEncoded
    @POST("admin/order/assignment")
    Call<ApproveCustomerResponse> assignEmployee(@Field("order_id") Integer orderId);


    @FormUrlEncoded
    @POST("employee/update")
    Call<UpdateEmployeeResponse> updateEmployeeType(@Field("type") String employeeType,@Field("mobile")String employeePhone);

    @GET("admin/order/list")
    Call<OrderResponse> getAllOrders();

    @FormUrlEncoded
    @POST("admin/login")
    Call<AuthenticationResponse> login(@Field("mobile") String mobile, @Field("password") String password);

    @GET("csrf")
    Call<String> getCSRFToken();

    @GET("csrf")
    Call<CSRFToken> getCsrfToken();

    @FormUrlEncoded
    @POST("admin/changePassword")
    Call<AuthenticationResponse> changePassword(@Field("current_password") String currentPassword, @Field("new_password") String newPassword);

    @GET("admin/order/reviews/list")
    Call<ReviewMsg> getReviews(@Query("order_id") Integer orderId);

    @FormUrlEncoded
    @POST("admin/order/reviews/add")
    Call<SubmitReviewResponse> submitReviews(@Field("order_id") Integer orderId, @Field("added_by_type") String addByType, @Field("remarks") String reviews);

    @FormUrlEncoded
    @POST("admin/order/delete?")
    Call<DeleteCustomerResponse> deleteOrder(@Field("order_id") Integer orderId);

   /* @Multipart
    @POST("admin/order/uploadInvoice")
    Call<UploadInvoice> uploadInvoice(@Part MultipartBody.Part invoice);*/

}
