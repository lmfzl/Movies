package com.example.movies.data.local;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movies.data.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase instance=null;
    private static final String DB_NAME  = "favourite_movies.db";
    public static MovieDatabase getInstance(Application application){
        if (instance== null){
            instance= Room.databaseBuilder(application, MovieDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    abstract MovieDao movieDao();
}
