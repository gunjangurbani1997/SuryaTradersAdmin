package com.example.suryatradersadmin.repository;

import android.util.Log;

import com.example.suryatradersadmin.api.RetroFitClient;
import com.example.suryatradersadmin.model.Admin;
import com.example.suryatradersadmin.model.AdminResponse;
import com.example.suryatradersadmin.model.AdminResult;
import com.example.suryatradersadmin.model.AuthenticationResponse;
import com.example.suryatradersadmin.model.AuthenticationResult;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.Original;
import com.example.suryatradersadmin.model.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRepository {

    private MutableLiveData<List<Admin>> mutableAdminList;
    private static final String TAG = "AdminRepository ";

    public MutableLiveData<Map<String,String>> ACCESS_TOKEN;
    public MutableLiveData<CSRFToken> CSRF_TOKEN=null;
    public HashMap<String,String> accessTokenMap=null;
    String csrfToken;
;
    public LiveData<Map<String, String>> getAccessToken()
    {
        return ACCESS_TOKEN;
    }

    public LiveData<List<Admin>> getAllAdmin()
    {
       /* if (mutableAdminList==null)
        {*/
            mutableAdminList=new MutableLiveData<>();
            loadAdminList();
     /*   }*/
        return mutableAdminList;
    }

    private void loadAdminList()
    {

        Call<AdminResponse> call= RetroFitClient.getInstance().getApi().getAllAdmin();
        call.enqueue(new Callback<AdminResponse>() {

            @Override
            public void onResponse(Call<AdminResponse> call, Response<AdminResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"Admin List ");
                    System.out.println(response.body());

                    AdminResponse adminResponse=response.body();
                    AdminResult adminResult=adminResponse.getAdminResponseResult();
                    List<Admin> adminList=adminResult.getAdmin();

                    Log.d(TAG,"Id "+  adminList.get(0).getAdminId()+" "+"Name "+  adminList.get(0).getAdminFirstName());
                    mutableAdminList.setValue(adminList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch admin list from server "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AdminResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to  admin list from server");
            }
        });

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

    public void login(final String mobileNo, String password, final MutableLiveData<Boolean> isSuccessful,final MutableLiveData<String>  authenticationFailure) {

        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().login(mobileNo, password);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                AuthenticationResponse authenticationResponse=response.body();
                AuthenticationResult authenticationResult=authenticationResponse.getResult();

                if(response.body().getStatus()==200) {
                    String customerId = authenticationResult.getAdmin_id();
                    Token token = authenticationResult.getToken();
                    Original original = token.getOriginal();
                    String accessToken = original.getAccessToken();
                    accessTokenMap = new HashMap<String, String>();
                    accessTokenMap.put(customerId, accessToken);
                    ACCESS_TOKEN = new MutableLiveData<Map<String, String>>();
                    ACCESS_TOKEN.setValue(accessTokenMap);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onSuccess: admin login success" + response.code());
                }
                else
                {
                    String message=authenticationResult.getAuthenticationMsg();
                    authenticationFailure.postValue(message);
                    Log.d(TAG, "onFailure: admin login failure- "+message +" "+ response.body().getStatus());
                }

            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                android.util.Log.d(TAG, "onFailure:"+t.getCause());
                android.util.Log.d(TAG, "onFailure: failed to login user");
            }
        });

    }

    public void changePassword( String currentPassword,String newPassword,final MutableLiveData<Boolean> isSuccessful, final MutableLiveData<String> changePasswordMsg)
    {
        CSRFToken();
        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().changePassword(currentPassword,newPassword);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if (response.isSuccessful())
                {
                    AuthenticationResponse authenticationResponse = response.body();
                    AuthenticationResult authenticationResult = authenticationResponse.getResult();
                    String msg = authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onSuccess: change password success " + response.code());
                }
                else
                {
                    AuthenticationResponse authenticationResponse=response.body();
                    AuthenticationResult authenticationResult=authenticationResponse.getResult();
                    String msg=authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(false);
                    Log.d(TAG, "onFailure: failed to change password "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to change password ");
            }
        });
    }
}
