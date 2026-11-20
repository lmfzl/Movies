package com.example.movies.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.data.remote.ApiFactory;
import com.example.movies.data.Movie;
import com.example.movies.data.local.MovieDao;
import com.example.movies.data.local.MovieDatabase;
import com.example.movies.data.remote.dto.Review;
import com.example.movies.data.remote.dto.ReviewResponse;
import com.example.movies.data.remote.dto.Trailer;
import com.example.movies.data.remote.dto.TrailerResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends AndroidViewModel {
    private static final String TAG = MovieDetailsActivity.class.getName();
    private final CompositeDisposable compositeDisposable=new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailers=new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews= new MutableLiveData<>();
    private final MovieDao movieDao;


    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieDao= MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public LiveData<Movie> getFavouriteMovie(int movieId) {
        return movieDao.getFavouriteMovie(movieId);
    }

    public void insertMovie(Movie movie){
        Disposable disposable=movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int id){
        Disposable disposable=movieDao.removeMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d(TAG, "Удаление завершено, id=" + id);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e(TAG, "Ошибка удаления, id=" + id + throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadTrailers(int id){
        Disposable disposable= ApiFactory.API_SERVICE.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        return trailerResponse.getTrailersList().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailersList) throws Throwable {
                        trailers.setValue(trailersList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }
    public void loadReviews(int id) {
        Disposable disposable=ApiFactory.API_SERVICE.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewResponse, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewResponse reviewResponse) throws Throwable {
                        return reviewResponse.getReviews();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviewsList) throws Throwable {
                        reviews.setValue(reviewsList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d (TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
