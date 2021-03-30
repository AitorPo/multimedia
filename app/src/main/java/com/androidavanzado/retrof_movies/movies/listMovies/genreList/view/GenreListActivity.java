package com.androidavanzado.retrof_movies.movies.listMovies.genreList.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.adapters.GenreListAdapter;
import com.androidavanzado.retrof_movies.adapters.TopRatedAdapter;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.retrof_movies.movies.genres.view.GenresActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.presenter.GenreListPresenter;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.view.TopRatedActivity;
import com.androidavanzado.retrof_movies.utils.OnItemClickListener;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.GENRE_ID;
import static com.androidavanzado.retrof_movies.utils.Constants.MOVIE_ID;

public class GenreListActivity extends AppCompatActivity implements GenreListContract.View, OnItemClickListener {
    private static final String TAG = "GenreListActivity";
    private GenreListPresenter presenter;
    private GenreListAdapter adapter;
    private ProgressBar pbProgress;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Movie> genreListArrayList;

    private Button btnRetry;
    private View llError;
    private TextView tvError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();
        setListeners();
        presenter = new GenreListPresenter(this);
        presenter.getGenreList();
    }

    private void setListeners() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbProgress.setVisibility(View.VISIBLE);
                hideError();

                presenter.getGenreList();
            }
        });
    }

    private void initComponents(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        genreListArrayList = new ArrayList<>();

        llError = findViewById(R.id.llMovieListError);
        btnRetry = findViewById(R.id.btnMovieListRetry);
        tvError = findViewById(R.id.tvMovieListError);

        refreshLayout = findViewById(R.id.refreshPopularList);

        pbProgress = findViewById(R.id.pbProgress);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GenreListAdapter(genreListArrayList, this, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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
        pbProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);

        genreListArrayList.addAll(genreMovies);
        adapter.notifyDataSetChanged();

        refreshLayout.setOnRefreshListener(() -> {
            genreListArrayList.clear();
            genreListArrayList.addAll(genreMovies);
            Log.d(TAG, "Lista refrescada");
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        showError();
        Toast.makeText(this, "Error al recibir los datos de la API", Toast.LENGTH_LONG).show();
    }

    public void showError() {
        Toast.makeText(this, "Comprueba tu conexión a internet", Toast.LENGTH_LONG).show();
        pbProgress.setVisibility(View.GONE);

        recyclerView.setVisibility(View.GONE);
        llError.setVisibility(View.VISIBLE);

        tvError.setText("Fallo de conexión");
    }


    public void hideError() {
        llError.setVisibility(View.GONE);
    }

    @Override
    public void onCardClick(int position) {
        genreListArrayList.get(position);
        Intent toDetaildIntent = new Intent(GenreListActivity.this, DetailsMovieActivity.class);
        int idMovie = genreListArrayList.get(position).getId();
        toDetaildIntent.putExtra(MOVIE_ID, idMovie);
        startActivity(toDetaildIntent);
    }
}
