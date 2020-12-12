package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailSMovie.view.DetailsActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.contract.MovieContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.presenter.MoviePresenter;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements MovieContract.View{
    private MoviePresenter presenter;
    private MovieListAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public static final String EXTRA_MESSAGE = "Movie.MESSAGE";
    public static final String EXTRA_MESSAGE_IMAGE = "Movie.MESSAGE_IMAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_list);

        presenter = new MoviePresenter(this);
        presenter.getMovieList();
    }

    @Override
    public void onSuccess(ArrayList<Movie> movies) {
       //Obtenemos el recycler
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(movies, this, new MovieListAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent intent = new Intent(MovieListActivity.this, DetailsActivity.class);
                //Obtenemos los datos de la película "clicada"
                String movieTitle = movies.get(position).getTitle();
                String image = movies.get(position).getPoster_path();
                //Pasamos los datos de la película "clicada" al activity de detalles
                intent.putExtra(EXTRA_MESSAGE, movieTitle);
                intent.putExtra(EXTRA_MESSAGE_IMAGE, image);
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