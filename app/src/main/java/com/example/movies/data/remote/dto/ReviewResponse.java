package com.example.movies.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReviewResponse implements Serializable {
    @SerializedName("docs")
    private List<Review> reviews;
    @SerializedName("next")
    private String nextCursor;
    @SerializedName("hasNext")
    private String hasNextCursor;

    public ReviewResponse(List<Review> reviews, String nextCursor, String hasNextCursor) {
        this.reviews = reviews;
        this.nextCursor = nextCursor;
        this.hasNextCursor = hasNextCursor;
    }

    @Override
    public String toString() {
        return "ReviewList{" +
                "reviewList=" + reviews +
                ", nextCursor='" + nextCursor + '\'' +
                ", hasNextCursor='" + hasNextCursor + '\'' +
                '}';
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public String getHasNextCursor() {
        return hasNextCursor;
    }

    public List<Review> getReviews() {
        return reviews;
    }


}
