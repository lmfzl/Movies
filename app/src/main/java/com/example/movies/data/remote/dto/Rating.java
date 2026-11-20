package com.example.movies.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("imdb")
    private final double imdbRating;

    public Rating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "imdbRating='" + imdbRating + '\'' +
                '}';
    }
}
