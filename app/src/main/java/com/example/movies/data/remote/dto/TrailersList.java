package com.example.movies.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailersList implements Serializable {
    @SerializedName("trailers")
    List<Trailer> trailers;

    public TrailersList(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "TrailersList{" +
                "trailers=" + trailers +
                '}';
    }
}
