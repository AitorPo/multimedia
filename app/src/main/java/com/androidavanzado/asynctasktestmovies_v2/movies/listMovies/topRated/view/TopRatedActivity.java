package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.view.GenresActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListAdapter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.contract.TopRatedContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.presenter.TopRatedPresenter;

import java.util.ArrayList;

public class TopRatedActivity extends AppCompatActivity implements TopRatedContract.View {
    private TopRatedPresenter presenter;
    private TopRatedAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public static int idTopRatedMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new TopRatedPresenter(this);
        presenter.getTopRatedMovies();

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
            Intent intent = new Intent(TopRatedActivity.this, GenresActivity.class);
            startActivity(intent);

        } else if (id == R.id.itemMostRated){

            Intent intent = new Intent(TopRatedActivity.this, TopRatedActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSuccess(ArrayList<Movie> topRatedMovies) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new TopRatedAdapter(topRatedMovies, this, new TopRatedAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent intent = new Intent(TopRatedActivity.this, DetailsMovieActivity.class);

                idTopRatedMovie = topRatedMovies.get(position).getId();
                MovieListActivity.idMovie = idTopRatedMovie;
                //intent.getIntExtra("MOVIE_ID", idMovie);
                //String image = movies.get(position).getPoster_path();
                //Pasamos los datos de la película "clicada" al activity de detalles
                //intent.putExtra("DETAILS_MOVIE", position);
                /*intent.putExtra(EXTRA_MESSAGE, movieTitle);
                intent.putExtra(EXTRA_MESSAGE_ID, idMovie);
                intent.putExtra(EXTRA_MESSAGE_IMAGE, image);*/
                //Iniciamos el intent
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String message) {

    }
}
