package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.model.DetailsMovieModel;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.presenter.GenreListPresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;

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
        setContentView(R.layout.fragment_movie_list);

        presenter = new GenreListPresenter(this);
        presenter.getGenreList();
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
                idFromGenre = genreMovies.get(position).getId();
                MovieListActivity.idMovie = idFromGenre;
                startActivity(intent);
                //idFromGenre = MovieListActivity.idMovie;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String message) {

    }
}
