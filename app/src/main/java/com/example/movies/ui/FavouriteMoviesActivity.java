package com.example.movies.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.data.Movie;
import com.example.movies.R;

import java.util.List;

public class FavouriteMoviesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFavouriteMovies;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_favourite_movies);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerViewFavouriteMovies), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        moviesAdapter=new MoviesAdapter();
        recyclerViewFavouriteMovies.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewFavouriteMovies.setAdapter(moviesAdapter);
        FavouriteMoviesViewModel viewModel= new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
        moviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onClick(Movie movie) {
                startActivity(MovieDetailsActivity.newIntent(FavouriteMoviesActivity.this, movie));
            }
        });

    }





    private void initViews(){
        recyclerViewFavouriteMovies=findViewById(R.id.recyclerViewFavouriteMovies);
    }
    public static Intent newIntent(Context context){
        return new Intent(context, FavouriteMoviesActivity.class);
    }
}