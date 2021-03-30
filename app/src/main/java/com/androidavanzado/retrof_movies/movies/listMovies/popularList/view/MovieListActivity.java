package com.androidavanzado.retrof_movies.movies.listMovies.popularList.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.androidavanzado.retrof_movies.R;
import com.androidavanzado.retrof_movies.adapters.MovieListAdapter;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.retrof_movies.movies.genres.view.GenresActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.presenter.MoviePresenter;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.contract.MovieContract;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.view.TopRatedActivity;
import com.androidavanzado.retrof_movies.utils.OnItemClickListener;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.MOVIE_ID;

public class MovieListActivity extends AppCompatActivity implements MovieContract.View, OnItemClickListener {
    private static final String TAG = "MovieListActivity";
    //public static int idMovie;

    private MoviePresenter presenter;
    private MovieListAdapter adapter;
    private RecyclerView rvMovies;
    private ProgressBar pbProgress;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Movie> movieArrayList;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);

        initComponents();

        setListeners();

        presenter = new MoviePresenter(this);
        presenter.getMovieList(this);
    }

    private void initComponents(){
        //Obtenemos el recycler
        rvMovies = findViewById(R.id.recyclerView);
        rvMovies.setHasFixedSize(true);
        movieArrayList = new ArrayList<>();

        llError = findViewById(R.id.llMovieListError);
        btnRetry = findViewById(R.id.btnMovieListRetry);
        tvError = findViewById(R.id.tvMovieListError);

        refreshLayout = findViewById(R.id.refreshPopularList);

        pbProgress = findViewById(R.id.pbProgress);

        layoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(movieArrayList, this, this);
        rvMovies.setItemAnimator(new DefaultItemAnimator());
        rvMovies.setAdapter(adapter);

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
        pbProgress.setVisibility(View.GONE);
        rvMovies.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);

        movieArrayList.addAll(movies);
        adapter.notifyDataSetChanged();

        page ++;
        Log.d(TAG, String.valueOf(page));
        Log.d(TAG, String.valueOf(movieArrayList.size()));

        refreshLayout.setOnRefreshListener(() -> {
            movieArrayList.clear();
            movieArrayList.addAll(movies);
            Log.d(TAG, "Lista refrescada");
            refreshLayout.setRefreshing(false);
        });

    }

    private void setListeners(){
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbProgress.setVisibility(View.VISIBLE);
                hideError();

                presenter.getMovieList(MovieListActivity.this);
            }
        });

        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = rvMovies.getChildCount();
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
                    presenter.getMoreMovies(getBaseContext(), page);
                    loading = true;
                }

            }
        });
    }

    @Override
    public void onFailure(Throwable throwable) {
        showError();
    }


    public void showError() {
        Toast.makeText(this, "Comprueba tu conexión a internet", Toast.LENGTH_LONG).show();
        pbProgress.setVisibility(View.GONE);

        rvMovies.setVisibility(View.GONE);
        llError.setVisibility(View.VISIBLE);

        tvError.setText("Fallo de conexión");
    }


    public void hideError() {
        llError.setVisibility(View.GONE);
        }


    @Override
    public void onCardClick(int position) {
        movieArrayList.get(position);
        Intent toDetaildIntent = new Intent(MovieListActivity.this, DetailsMovieActivity.class);
        int idMovie = movieArrayList.get(position).getId();
        toDetaildIntent.putExtra(MOVIE_ID, idMovie);
        startActivity(toDetaildIntent);
    }
}