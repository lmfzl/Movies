package com.example.movies.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("author")
    private String author;
    @SerializedName("type")
    private String reviewType;
    @SerializedName("review")
    private String review;

    public Review(String author, String reviewType, String review) {
        this.author = author;
        this.reviewType = reviewType;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewType() {
        return reviewType;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", reviewType='" + reviewType + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
