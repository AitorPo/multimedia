package com.androidavanzado.asynctasktestmovies_v2.movies.genres.view;

import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.contract.GenresContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.presenter.GenresPresenter;


import java.util.ArrayList;

public class GenresActivity extends AppCompatActivity implements GenresContract.View{
    private RecyclerView recyclerViewGenre;
    private RecyclerView.LayoutManager layoutManager;
    private GenresPresenter presenter;
    private GenresAdapter adapter;
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

        layoutManager = new LinearLayoutManager(this);
        recyclerViewGenre.setLayoutManager(layoutManager);

       adapter = new GenresAdapter(genres, this, new GenresAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {

            }
        });
       recyclerViewGenre.setAdapter(adapter);

    }

    @Override
    public void onFailure(String message) {

    }
}
