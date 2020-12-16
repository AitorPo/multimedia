package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view;

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
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.presenter.GenreListPresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.view.TopRatedActivity;

import java.util.ArrayList;

public class GenreListActivity extends AppCompatActivity implements GenreListContract.View {
    private GenreListPresenter presenter;
    private GenreListAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static int idFromGenre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new GenreListPresenter(this);
        presenter.getGenreList();
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
            Intent intent = new Intent(GenreListActivity.this, GenresActivity.class);
            startActivity(intent);

        } else if (id == R.id.itemMostRated){
            Intent intent = new Intent(GenreListActivity.this, TopRatedActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<Movie> genreMovies) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GenreListAdapter(genreMovies, this, new GenreListAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {

                Intent intent = new Intent(GenreListActivity.this, DetailsMovieActivity.class);
                //Obtenemos el id de la película clicada
                idFromGenre = genreMovies.get(position).getId();
                //Igualamos el id de la pelicula clicada en la lista de peliculas por genero a
                //la variable static de idMovie para pasarsela por parametro a la URL del Model
                MovieListActivity.idMovie = idFromGenre;
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String message) {

    }
}
