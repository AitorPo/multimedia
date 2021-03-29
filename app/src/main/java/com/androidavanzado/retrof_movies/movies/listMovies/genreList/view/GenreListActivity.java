package com.androidavanzado.retrof_movies.movies.listMovies.genreList.view;

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
import com.androidavanzado.retrof_movies.adapters.GenreListAdapter;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.retrof_movies.movies.genres.view.GenresActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.presenter.GenreListPresenter;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.view.TopRatedActivity;

import java.util.ArrayList;

import static com.androidavanzado.retrof_movies.utils.Constants.GENRE_ID;
import static com.androidavanzado.retrof_movies.utils.Constants.MOVIE_ID;

public class GenreListActivity extends AppCompatActivity implements GenreListContract.View {
    private static final String TAG = "GenreListActivity";
    private GenreListPresenter presenter;
    private GenreListAdapter adapter;
    private ProgressBar pbProgress;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        presenter = new GenreListPresenter(this);
        presenter.getGenreList();
    }

    private void initComponents(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
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
            Intent intent = new Intent(GenreListActivity.this, GenresActivity.class);
            startActivity(intent);

        } else if (id == R.id.itemMostRated){
            Intent intent = new Intent(GenreListActivity.this, TopRatedActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }

    private void setDataInRecyclerView(ArrayList<Movie> genreMovies){
        adapter = new GenreListAdapter(genreMovies, this, new GenreListAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {

                Intent toDetaildIntent = new Intent(GenreListActivity.this, DetailsMovieActivity.class);
                //Obtenemos el id de la película clicada
                int idFromGenre = genreMovies.get(position).getId();
                toDetaildIntent.putExtra(MOVIE_ID, idFromGenre);
                //Igualamos el id de la pelicula clicada en la lista de peliculas por genero a
                //la variable static de idMovie para pasarsela por parametro a la URL del Model
                //MovieListActivity.idMovie = idFromGenre;
                startActivity(toDetaildIntent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(() -> {
            setDataInRecyclerView(genreMovies);
            Log.d(TAG, "Lista de películas por género refrescada");
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onSuccess(ArrayList<Movie> genreMovies) {
        setDataInRecyclerView(genreMovies);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, "Error al recibir los datos de la API", Toast.LENGTH_LONG).show();
    }


}
