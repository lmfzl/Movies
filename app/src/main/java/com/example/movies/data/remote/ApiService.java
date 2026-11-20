package com.example.movies.data.remote;

import com.example.movies.data.remote.dto.MovieResponse;
import com.example.movies.data.remote.dto.ReviewResponse;
import com.example.movies.data.remote.dto.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?notNullFields=videos.trailers.url&notNullFields=poster.url&type=movie&year=2024-2026&rating.imdb=1-10&limit=50&sortField=fees.world.value&sortType=-1&withCount=false")
    Single<MovieResponse> loadMovies(@Query("next") String nextPageId);

    @GET("movie/{movieId}")
    Single<TrailerResponse> loadTrailers(@Path("movieId") int movieId);

    @GET("review?limit=50")
    Single<ReviewResponse> loadReviews(@Query("movieId") int movieId);


}
