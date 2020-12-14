package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidavanzado.asynctasktestmovies_v2.R;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.view.DetailsMovieActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.view.GenresActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.presenter.MoviePresenter;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.contract.MovieContract;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements MovieContract.View{
    Spinner spinner;
    private MoviePresenter presenter;
    private MovieListAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public static final String EXTRA_MESSAGE = "Movie.MESSAGE";
    public static final String EXTRA_MESSAGE_IMAGE = "Movie.MESSAGE_IMAGE";
    public static final String EXTRA_MESSAGE_ID = "Movie.MESSAGE_ID";

    public static int idMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_movie_list);

        String[] sFilter = {"Selecciona","Género", "Mejor valorada"};
        presenter = new MoviePresenter(this);
        presenter.getMovieList();
        spinner = findViewById(R.id.genreSpinner);
        spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, sFilter));


        }

        public void onItemSelected(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Intent intent = new Intent(MovieListActivity.this, GenresActivity.class);
                        startActivity(intent);

                        break;
                    case 2:
                        //TODO intent hacia la lista de películas mejor valoradas
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});}




    @Override
    public void onSuccess(ArrayList<Movie> movies) {
       //Obtenemos el recycler
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        onItemSelected();


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new MovieListAdapter(movies, this, new MovieListAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                Intent intent = new Intent(MovieListActivity.this, DetailsMovieActivity.class);
                //Obtenemos los datos de la película "clicada"
                //String movieTitle = movies.get(position).getTitle();
                idMovie = movies.get(position).getId();
                intent.getIntExtra("MOVIE_ID", idMovie);
                //String image = movies.get(position).getPoster_path();
                //Pasamos los datos de la película "clicada" al activity de detalles
                //intent.putExtra("DETAILS_MOVIE", position);
                /*intent.putExtra(EXTRA_MESSAGE, movieTitle);
                intent.putExtra(EXTRA_MESSAGE_ID, idMovie);
                intent.putExtra(EXTRA_MESSAGE_IMAGE, image);*/
                //Iniciamos el intent
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String message) {

    }
}