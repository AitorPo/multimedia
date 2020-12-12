package com.androidavanzado.asynctasktestmovies_v2.movies.detailSMovie.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.view.MovieListActivity;
import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_item);

        //Capturamos los datos de la película clicada y su intent
        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra(MovieListActivity.EXTRA_MESSAGE);
        String image = intent.getStringExtra(MovieListActivity.EXTRA_MESSAGE_IMAGE);
        //Vinculamos los elementos al layout
        ImageView ivPoster = findViewById(R.id.ivPoster);
        TextView tvTitle = findViewById(R.id.tvTitle);
        //Seteamos los valores de los elementos del layout
        tvTitle.setText(movieTitle);
        Glide.with(this).load("https://image.tmdb.org/t/p/original" + image)
                .into(ivPoster);
    }

}
