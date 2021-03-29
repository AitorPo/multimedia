package com.androidavanzado.retrof_movies.movies.listMovies.topRated.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.MOVIE_ID;

public class TopRatedActivity extends AppCompatActivity implements TopRatedContract.View {
    private static final String TAG = "TopRatedActivity";
    private TopRatedPresenter presenter;
    private TopRatedAdapter adapter;
    private ProgressBar pbProgress;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Movie> topRatedMovies;
    private RecyclerView.LayoutManager layoutManager;
    public static int idTopRatedMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        presenter = new TopRatedPresenter(this);
        presenter.getTopRatedMovies();

    }

    private void initComponents(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        topRatedMovies = new ArrayList<>();


        refreshLayout = findViewById(R.id.refreshPopularList);

        pbProgress = findViewById(R.id.pbProgress);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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
    public void showEmptyView() {

    }

    @Override
    public void hideEmptyView() {

    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }

    private void setDataInRecyclerView(ArrayList<Movie> topRatedMovies){
        adapter = new TopRatedAdapter(topRatedMovies, this, new TopRatedAdapter.OnItemClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent toDetaildIntent = new Intent(TopRatedActivity.this, DetailsMovieActivity.class);
                //Obtenemos los datos de la película "clicada"
                //String movieTitle = movies.get(position).getTitle();
                int idMovie = topRatedMovies.get(position).getId();
                toDetaildIntent.putExtra(MOVIE_ID, idMovie);
                //String image = movies.get(position).getPoster_path();
                //Pasamos los datos de la película "clicada" al activity de detalles
                //intent.putExtra("DETAILS_MOVIE", position);
                /*intent.putExtra(EXTRA_MESSAGE, movieTitle);
                intent.putExtra(EXTRA_MESSAGE_ID, idMovie);
                intent.putExtra(EXTRA_MESSAGE_IMAGE, image);*/
                //Iniciamos el intent
                startActivity(toDetaildIntent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(() -> {
            setDataInRecyclerView(topRatedMovies);
            Log.d(TAG, "Lista topRated refrescada");
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onSuccess(ArrayList<Movie> topRatedMovies) {
        setDataInRecyclerView(topRatedMovies);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, "Error al recibir los datos de la API", Toast.LENGTH_LONG).show();
    }
}
