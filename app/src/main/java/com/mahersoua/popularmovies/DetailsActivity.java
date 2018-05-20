package com.mahersoua.popularmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mahersoua.popularmovies.adapters.MovieCatalogAdapter;
import com.mahersoua.popularmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    String URL = "http://image.tmdb.org/t/p/w500/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(null!= getIntent().getExtras()){
            Parcelable parcelable = getIntent().getExtras().getParcelable(MovieCatalogAdapter.MOVIE_EXTRAS);
            MovieModel  movieModel = (MovieModel) parcelable;
            TextView movieNameTv = findViewById(R.id.movieName);
            RatingBar ratingBar = findViewById(R.id.ratingBar);
            TextView releaseDate = findViewById(R.id.releaseDate);
            TextView synopsisTv = findViewById(R.id.synopsisTv);


            movieNameTv.setText(movieModel.getTitle());
            ratingBar.setRating(movieModel.getVoteAverage());
            releaseDate.setText(movieModel.getReleaseDate());
            synopsisTv.setText(movieModel.getOverview());

            ImageView posterContainer = findViewById(R.id.detailsPosterContainer);
            Picasso.get().load(URL+movieModel.getPosterPath()).into(posterContainer);

        }



    }
}
