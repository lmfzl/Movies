package com.example.movies.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrailerResponse implements Serializable {
    @SerializedName("videos")
    TrailersList trailersList;

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "videos=" + trailersList +
                '}';
    }

    public TrailersList getTrailersList() {
        return trailersList;
    }

    public TrailerResponse(TrailersList videos) {
        this.trailersList = videos;
    }
}

