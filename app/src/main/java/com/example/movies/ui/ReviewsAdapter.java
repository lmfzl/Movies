package com.example.movies.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.data.remote.dto.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsHolder> {
    private List<Review> reviews=new ArrayList<>();
    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEUTRAL = "Нейтральный";
    private static final String TYPE_NEGATIVE = "Негативный";

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent,false);
        return new ReviewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsHolder holder, int position) {
        Review review=reviews.get(position);
        holder.textViewName.setText(review.getAuthor());
        holder.textViewReview.setText(review.getReview());

        int resId;
        if (review.getReviewType().equals(TYPE_POSITIVE)){
            resId= android.R.color.holo_green_light;
        } else if (review.getReviewType().equals(TYPE_NEUTRAL)) {
            resId= android.R.color.holo_orange_light;
        }else {
            resId = android.R.color.holo_red_light;
        }
        int color= ContextCompat.getColor(holder.itemView.getContext(), resId);
        holder.cardView.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewsHolder extends RecyclerView.ViewHolder{
        private final TextView textViewName;
        private final TextView textViewReview;
        private final CardView cardView;
        public ReviewsHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textViewReviewName);
            textViewReview=itemView.findViewById(R.id.textViewReview);
            cardView=itemView.findViewById(R.id.cardViewReview);
        }
    }
}
