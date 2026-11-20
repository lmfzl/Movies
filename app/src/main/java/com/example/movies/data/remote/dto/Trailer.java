package com.example.movies.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {
    @SerializedName("url")
    String trailerUrl;
    @SerializedName("name")
    String trailerName;

    public Trailer(String trailerUrl, String trailerName) {
        this.trailerUrl = trailerUrl;
        this.trailerName = trailerName;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public String getTrailerName() {
        return trailerName;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "trailerUrl='" + trailerUrl + '\'' +
                ", trailerName='" + trailerName + '\'' +
                '}';
    }
}
