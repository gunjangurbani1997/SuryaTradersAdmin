package com.example.suryatradersadmin.repository;

import android.util.Log;


import com.example.suryatradersadmin.api.RetroFitClient;
import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.model.Review;
import com.example.suryatradersadmin.model.ReviewMsg;
import com.example.suryatradersadmin.model.ReviewResult;
import com.example.suryatradersadmin.model.SubmitReviewResponse;
import com.example.suryatradersadmin.model.SubmitReviewResult;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepo {


    private static final String TAG = "ReviewRepo ";
    private MutableLiveData<List<Review>> mutableReviews= new MutableLiveData<>();
    public MutableLiveData<CSRFToken> CSRF_TOKEN=null;
    private MutableLiveData<String> mutableSubmitReviews= new MutableLiveData<>();
    String csrfToken;
    public LiveData<List<Review>> getReviewOfOrder(Integer orderId) {
        getReviews(orderId);
        return mutableReviews;

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


    public void getReviews(Integer orderId) {

        Call<ReviewMsg> call = RetroFitClient.getInstance().getApi().getReviews(orderId);
        call.enqueue(new Callback<ReviewMsg>() {

            @Override
            public void onResponse(Call<ReviewMsg> call, Response<ReviewMsg> response) {
                if (response.isSuccessful()) {

                    Log.d(TAG, "In getReviews method");
                    System.out.println(response.body());

                    if (response.isSuccessful()) {
                        ReviewMsg reviewMsg = response.body();
                        ReviewResult reviewResult = reviewMsg.getReviewResult();
                        List<Review> reviewList = new ArrayList<>();
                        String msg = "";
                        if (reviewResult.getReview() != null) {
                            reviewList = reviewResult.getReview();
                            mutableReviews.setValue(reviewList);

                        }
                       /* else {
                            msg = getCartResult.getErrorMsg();
                            mutableErrorMsg.postValue(msg);
                            Log.d(TAG, "onFailure: failed to fetch cart list from server " +msg+" "+ response.body().getStatus());
                        }*/
                    }

                }

            }

            @Override
            public void onFailure(Call<ReviewMsg> call, Throwable t) {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:" + t.getCause());
                Log.d(TAG, "onFailure: cannot fetch reviews ");

            }

        });
    }

    public void submitReview(final Integer orderId, String message)
    {

        Call<SubmitReviewResponse> call = RetroFitClient.getInstance().getApi().submitReviews(orderId,"admin",message);
        call.enqueue(new Callback<SubmitReviewResponse>() {

            @Override
            public void onResponse(Call<SubmitReviewResponse> call, Response<SubmitReviewResponse> response) {
                if (response.isSuccessful()) {

                    Log.d(TAG, "In submitReview method");
                    System.out.println(response.body());

                    if (response.isSuccessful()) {
                        SubmitReviewResponse submitReviewResponse = response.body();
                        SubmitReviewResult submitReviewResult = submitReviewResponse.getResult();
                        String msg = "";
                        if (submitReviewResult.getSubmitReviewMsg() != null) {
                            msg=submitReviewResult.getSubmitReviewMsg();
                            mutableSubmitReviews.setValue(msg);
                            Log.d(TAG, " Review submitted: "+msg);
                            getReviews(orderId);
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<SubmitReviewResponse> call, Throwable t) {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:" + t.getCause());
                Log.d(TAG, "onFailure: cannot submit reviews ");

            }

        });
    }
}