package com.example.movies.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.data.Movie;
import com.example.movies.R;
import com.example.movies.data.remote.dto.Review;
import com.example.movies.data.remote.dto.Trailer;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private MovieDetailsViewModel viewModel;
    private RecyclerView recyclerViewTrailers;
    private TrailersAdapter trailersAdapter;
    private RecyclerView recyclerViewReviews;
    private ReviewsAdapter reviewsAdapter;
    private ImageView imageViewStar;

    private static final String EXTRA_MOVIE = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_movie_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        trailersAdapter = new TrailersAdapter();
        reviewsAdapter = new ReviewsAdapter();
        recyclerViewTrailers.setAdapter(trailersAdapter);
        recyclerViewReviews.setAdapter(reviewsAdapter);

        viewModel= new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        Movie movie=(Movie) getIntent().getSerializableExtra(EXTRA_MOVIE); // Без явного приведения
        // типа (Movie), возвращаемый объект будет типа Serializable. В более новых версиях апи,
        // надо объявлять так Movie movie = getIntent().getSerializableExtra("movie", Movie.class);

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into (imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });

        viewModel.loadReviews(movie.getId());
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewsAdapter.setReviews(reviews);
            }
        });

        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW); // СОЗДАНИЕ НЕЯВНОГО ИНТЕНТА
                intent.setData(Uri.parse(trailer.getTrailerUrl())); // .setData принимает Uri, а у
                // нас getTrailerUrl() возвращает стрингу, ее можно преобразовать в Uri при помощи
                // Uri.parse(*string*)
                startActivity(intent);
            }
        });
        Drawable starOff = ContextCompat.getDrawable(MovieDetailsActivity.this, android.R.drawable.star_big_off);
        Drawable starOn = ContextCompat.getDrawable(MovieDetailsActivity.this, android.R.drawable.star_big_on);
        viewModel.getFavouriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {
                if (movieFromDb==null){
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.insertMovie(movie);
                        }
                    });
                }else{
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.removeMovie(movie.getId());
                        }
                    });
                }
            }
        });

    }


    private void initViews(){
        imageViewStar =findViewById(R.id.imageViewStar);
        recyclerViewReviews=findViewById(R.id.recyclerViewReviews);
        imageViewPoster=findViewById(R.id.imageViewPoster);
        textViewTitle=findViewById(R.id.textViewTitle);
        textViewYear=findViewById(R.id.textViewYear);
        textViewDescription=findViewById(R.id.textViewDescription);
        recyclerViewTrailers=findViewById(R.id.recyclerViewTrailers);
    }
    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        // Если мы хотим передать в интент объект, то в его классе необходимо имплементировать
        // интерфейс Serializable. Если поля этого класса являются объектами, то в классах объектов,
        // так же необходимо реализовать Serializable
        intent.putExtra(EXTRA_MOVIE,movie);
        return intent;
    }
}