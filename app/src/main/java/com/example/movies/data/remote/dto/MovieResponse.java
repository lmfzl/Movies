package com.example.movies.data.remote.dto;

import com.example.movies.data.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("docs")
    private List<Movie> movies;
    @SerializedName("hasNext")
    private Boolean hasNextCursor;
    @SerializedName("next")
    private String nextCursor;

    public MovieResponse(List<Movie> movies, Boolean hasNext, String next) {
        this.movies = movies;
        this.hasNextCursor = hasNext;
        this.nextCursor = next;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Boolean getHasNextCursor() {
        return hasNextCursor;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movies=" + movies +
                ", hasNextPage=" + hasNextCursor +
                ", nextPageId='" + nextCursor + '\'' +
                '}';
    }
}
