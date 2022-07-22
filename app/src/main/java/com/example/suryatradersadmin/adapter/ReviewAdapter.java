package com.example.suryatradersadmin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.suryatradersadmin.databinding.ReviewRowBinding;
import com.example.suryatradersadmin.model.Review;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends ListAdapter<Review, ReviewAdapter.ReviewHolder> {

    private static final String TAG="ReviewAdapter ";
    ReviewInterface reviewInterface;
    public ReviewAdapter(ReviewInterface reviewInterface) {
        super(Review.itemCallback);
        this.reviewInterface=reviewInterface;
    }

    @NonNull
    @Override
    public com.example.suryatradersadmin.adapter.ReviewAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ReviewRowBinding reviewRowBinding=ReviewRowBinding.inflate(layoutInflater,parent,false);
        reviewRowBinding.setReviewInterface(reviewInterface);
        return new com.example.suryatradersadmin.adapter.ReviewAdapter.ReviewHolder(reviewRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.suryatradersadmin.adapter.ReviewAdapter.ReviewHolder holder, int position) {

        Review review=getItem(position);
        holder.reviewRowBinding.setReview(review);
    }

    public class ReviewHolder extends RecyclerView.ViewHolder
    {
        ReviewRowBinding reviewRowBinding;
        public ReviewHolder(@NonNull ReviewRowBinding reviewRowBinding) {
            super(reviewRowBinding.getRoot());
            this.reviewRowBinding=reviewRowBinding;
        }
    }

    public interface ReviewInterface
    {

    }
}
