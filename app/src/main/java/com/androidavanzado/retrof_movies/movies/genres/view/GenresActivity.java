package com.androidavanzado.retrof_movies.movies.genres.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.adapters.GenresAdapter;
import com.androidavanzado.retrof_movies.beans.Genre;
import com.androidavanzado.retrof_movies.movies.genres.contract.GenresContract;
import com.androidavanzado.retrof_movies.movies.genres.presenter.GenresPresenter;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.view.GenreListActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.view.TopRatedActivity;


import java.util.ArrayList;

public class GenresActivity extends AppCompatActivity implements GenresContract.View{
    private static final String TAG = "GenresActivity";
    private RecyclerView recyclerViewGenre;
    private RecyclerView.LayoutManager layoutManagerGenre;
    private ProgressBar pbProgressGenre;
    private GenresPresenter presenter;
    private GenresAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    public static int idGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genres_list);
        Toolbar toolbar = findViewById(R.id.tbGenre);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        presenter = new GenresPresenter(this);
        presenter.getGenres();
    }

    private void initComponents(){
        recyclerViewGenre = findViewById(R.id.recyclerViewGenre);
        recyclerViewGenre.setHasFixedSize(true);
        refreshLayout = findViewById(R.id.refreshGenreList);

        pbProgressGenre = findViewById(R.id.pbProgressGenre);

        layoutManagerGenre = new GridLayoutManager(this, 2);
        recyclerViewGenre.setLayoutManager(layoutManagerGenre);
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
    public void showProgress() {
        pbProgressGenre.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgressGenre.setVisibility(View.GONE);
    }

    private void setDataInRecyclerView(ArrayList<Genre> genres){
        adapter = new GenresAdapter(genres, this, new GenresAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent intent = new Intent(GenresActivity.this, GenreListActivity.class);
                idGenre = genres.get(position).getId();
                startActivity(intent);
            }
        });
        recyclerViewGenre.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            setDataInRecyclerView(genres);
            Log.d(TAG, "Lista de géneros refrescada");
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onSuccess(ArrayList<Genre> genres) {
        setDataInRecyclerView(genres);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, "Error al recibir los datos de la API", Toast.LENGTH_LONG).show();
    }
}
