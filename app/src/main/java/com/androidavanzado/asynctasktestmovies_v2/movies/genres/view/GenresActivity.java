package com.androidavanzado.asynctasktestmovies_v2.movies.genres.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.contract.GenresContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.presenter.GenresPresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view.GenreListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.view.TopRatedActivity;


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
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new GenresPresenter(this);
        presenter.getGenres();
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
            return false;
        } else if (id == R.id.itemMostRated){
            Intent intent = new Intent(GenresActivity.this, TopRatedActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
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
