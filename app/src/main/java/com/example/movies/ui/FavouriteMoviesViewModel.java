package com.example.movies.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movies.data.Movie;
import com.example.movies.data.local.MovieDao;
import com.example.movies.data.local.MovieDatabase;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FavouriteMoviesViewModel extends AndroidViewModel {

    private final MovieDao movieDao;
    private final CompositeDisposable compositeDisposable= new CompositeDisposable();

    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao= MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieDao.getAllFavouriteMovies();
    }
}
