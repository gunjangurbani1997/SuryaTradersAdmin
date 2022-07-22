package com.example.suryatradersadmin.viewmodel;



import com.example.suryatradersadmin.model.Review;
import com.example.suryatradersadmin.repository.ReviewRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewViewModel extends ViewModel {

    ReviewRepo reviewRepo=new ReviewRepo();

    public final MutableLiveData<String> reviewTextArea = new MutableLiveData<>();

    public LiveData<List<Review>> getReviews(Integer orderId)
    {
        return reviewRepo.getReviewOfOrder(orderId);
    }

    public void submitReview(Integer orderId,String msg)
    {
         reviewRepo.submitReview(orderId,msg);
    }

}
