package com.androidavanzado.retrof_movies.movies.listMovies.topRated.view;

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
import com.androidavanzado.retrof_movies.adapters.TopRatedAdapter;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.retrof_movies.movies.genres.view.GenresActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.contract.TopRatedContract;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.presenter.TopRatedPresenter;
import com.androidavanzado.retrof_movies.utils.OnItemClickListener;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.MOVIE_ID;

public class TopRatedActivity extends AppCompatActivity implements TopRatedContract.View, OnItemClickListener {
    private static final String TAG = "TopRatedActivity";
    private TopRatedPresenter presenter;
    private TopRatedAdapter adapter;
    private ProgressBar pbProgress;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Movie> topRatedMoviesArrayList;
    private LinearLayoutManager layoutManager;
    private Button btnRetry;
    private View llError;
    private TextView tvError;


    private int page = 1;

    // Gestión del scroll de paginado
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();
        setListeners();

        presenter = new TopRatedPresenter(this);
        presenter.getTopRatedMovies(this);

    }

    private void setListeners() {
        btnRetry.setOnClickListener(v -> {
            pbProgress.setVisibility(View.VISIBLE);
            hideError();

            presenter.getTopRatedMovies(TopRatedActivity.this);
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition();

                // Lógica de paginado al hacer scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    presenter.getMoreTopRatedMovies(getBaseContext(), page);
                    loading = true;
                }

            }
        });
    }

    private void initComponents(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        topRatedMoviesArrayList = new ArrayList<>();

        llError = findViewById(R.id.llMovieListError);
        btnRetry = findViewById(R.id.btnMovieListRetry);
        tvError = findViewById(R.id.tvMovieListError);

        refreshLayout = findViewById(R.id.refreshPopularList);

        pbProgress = findViewById(R.id.pbProgress);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TopRatedAdapter(topRatedMoviesArrayList, this, this);
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
        pbProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);

        topRatedMoviesArrayList.addAll(topRatedMovies);
        adapter.notifyDataSetChanged();

        page ++;
        Log.d(TAG, String.valueOf(page));
        Log.d(TAG, String.valueOf(topRatedMoviesArrayList.size()));

        refreshLayout.setOnRefreshListener(() -> {
            topRatedMoviesArrayList.clear();
            topRatedMoviesArrayList.addAll(topRatedMovies);
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
        topRatedMoviesArrayList.get(position);
        Intent toDetaildIntent = new Intent(TopRatedActivity.this, DetailsMovieActivity.class);
        int idMovie = topRatedMoviesArrayList.get(position).getId();
        toDetaildIntent.putExtra(MOVIE_ID, idMovie);
        startActivity(toDetaildIntent);
    }
}
