package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.view.GenresActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.presenter.MoviePresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.contract.MovieContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.view.TopRatedActivity;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements MovieContract.View{
    private MoviePresenter presenter;
    private MovieListAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public static final String EXTRA_MESSAGE = "Movie.MESSAGE";
    public static final String EXTRA_MESSAGE_IMAGE = "Movie.MESSAGE_IMAGE";
    public static final String EXTRA_MESSAGE_ID = "Movie.MESSAGE_ID";

    public static int idMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);


        presenter = new MoviePresenter(this);
        presenter.getMovieList();
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
            Intent intent = new Intent(MovieListActivity.this, GenresActivity.class);
            startActivity(intent);

        } else if (id == R.id.itemMostRated){
            Intent intent = new Intent(MovieListActivity.this, TopRatedActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<Movie> movies) {
       //Obtenemos el recycler
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //onItemSelected();


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new MovieListAdapter(movies, this, new MovieListAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent intent = new Intent(MovieListActivity.this, DetailsMovieActivity.class);
                //Obtenemos los datos de la película "clicada"
                //String movieTitle = movies.get(position).getTitle();
                idMovie = movies.get(position).getId();
                intent.getIntExtra("MOVIE_ID", idMovie);
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