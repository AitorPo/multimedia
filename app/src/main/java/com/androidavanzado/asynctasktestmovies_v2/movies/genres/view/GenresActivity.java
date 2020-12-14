package com.androidavanzado.asynctasktestmovies_v2.movies.genres.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.contract.GenresContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.presenter.GenresPresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view.GenreListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;


import java.util.ArrayList;

public class GenresActivity extends AppCompatActivity implements GenresContract.View{
    private RecyclerView recyclerViewGenre;
    private RecyclerView.LayoutManager layoutManagerGenre;
    private GenresPresenter presenter;
    private GenresAdapter adapter;

    public static int idGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genres_list);

        presenter = new GenresPresenter(this);
        presenter.getGenres();
    }
    @Override
    public void onSuccess(ArrayList<Genre> genres) {
        recyclerViewGenre = findViewById(R.id.recyclerViewGenre);
        recyclerViewGenre.setHasFixedSize(true);

        layoutManagerGenre = new GridLayoutManager(this, 2);
        recyclerViewGenre.setLayoutManager(layoutManagerGenre);

       adapter = new GenresAdapter(genres, this, new GenresAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent intent = new Intent(GenresActivity.this, GenreListActivity.class);
                idGenre = genres.get(position).getId();
                startActivity(intent);
            }
        });
       recyclerViewGenre.setAdapter(adapter);

    }

    @Override
    public void onFailure(String message) {

    }
}
