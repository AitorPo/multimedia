package com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.DetailsMovie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.contract.DetailsMovieContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.presenter.DetailsMoviePresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.view.GenresActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view.GenreListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.view.TopRatedActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsMovieActivity extends AppCompatActivity implements DetailsMovieContract.View {
    private DetailsMoviePresenter presenter;
    private TextView tvTitle;
    private ImageView ivPoster;
    private TextView tvVote;
    private TextView tvOverview;
    private TextView tvVoteCount;
    private TextView tvPopularity;
    private TextView tvOriginalLanguage;
    private TextView tvRuntime;
    private TextView tvOriginalTitle;
    private TextView tvHomepage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_item_details);
        Toolbar toolbar = findViewById(R.id.tbDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new DetailsMoviePresenter(this);
        presenter.getDetails();

        //Capturamos los datos de la película clicada y su intent
       /* Intent intent = getIntent();
        String movieTitle = intent.getStringExtra(MovieListActivity.EXTRA_MESSAGE);
        //Obtenemos el ID de la película pero lo tenemos que "parsear" a String para poder usarlo
        //String id = String.valueOf(intent.getIntExtra(MovieListActivity.EXTRA_MESSAGE_ID, 0));
        String image = intent.getStringExtra(MovieListActivity.EXTRA_MESSAGE_IMAGE);
        //Vinculamos los elementos al layout
        ImageView ivPoster = this.findViewById(R.id.ivPoster);
        TextView tvTitle = this.findViewById(R.id.tvTitle);
        //Seteamos los valores de los elementos del layout
        //tvTitle.setText(id);
        tvTitle.setText(movieTitle);
        Glide.with(this).load("https://image.tmdb.org/t/p/original" + image)
                .into(ivPoster);*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemGenre){
            Intent intent = new Intent(DetailsMovieActivity.this, GenresActivity.class);
            startActivity(intent);

        } else if (id == R.id.itemMostRated){
            Intent intent = new Intent(DetailsMovieActivity.this, TopRatedActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSuccess(ArrayList<DetailsMovie> details) {



        tvTitle = this.findViewById(R.id.tvGenre);
        ivPoster = this.findViewById(R.id.ivPoster);
        tvVote = this.findViewById(R.id.tvVote);
        tvOverview = this.findViewById(R.id.tvOverview);
        tvVoteCount = this.findViewById(R.id.tvVoteCount);
        tvPopularity = this.findViewById(R.id.tvPopularity);
        tvOriginalLanguage = this.findViewById(R.id.tvOriginalLanguage);
        tvRuntime = this.findViewById(R.id.tvRuntime);
        tvOriginalTitle = this.findViewById(R.id.tvOriginalTitle);
        tvHomepage = this.findViewById(R.id.tvHomepage);
        for(DetailsMovie detailsMovie : details){
            tvTitle.setText(detailsMovie.getTitle());
            tvVote.setText(String.valueOf(detailsMovie.getVoteAverage()));
            Glide.with(this).load("https://image.tmdb.org/t/p/original" + detailsMovie.getPoster_path())
                    .into(ivPoster);
            tvOverview.setText(detailsMovie.getOverview());
            tvVoteCount.setText(String.valueOf(detailsMovie.getVote_count()));
            tvPopularity.setText(String.valueOf(detailsMovie.getPopularity()));
            tvOriginalLanguage.setText(detailsMovie.getOriginal_language().replace("en", "Inglés"));
            tvRuntime.setText(detailsMovie.getRuntime() + " minutos");
            tvOriginalTitle.setText(detailsMovie.getOriginal_title());
            tvHomepage.setText(detailsMovie.getHomepage());
        }

    }

    @Override
    public void onFailure(String message) {
    }
}
