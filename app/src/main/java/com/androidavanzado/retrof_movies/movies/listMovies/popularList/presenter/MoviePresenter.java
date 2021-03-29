package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.presenter;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.contract.MovieContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.model.MovieModel;

import java.util.ArrayList;

public class MoviePresenter implements MovieContract.Presenter {
    private MovieModel movieModel;
    private MovieContract.View view;


    public MoviePresenter(MovieContract.View view){
        this.view = view;
        this.movieModel = new MovieModel();
    }

    @Override
    public void getMovieList() {
        movieModel.getMoviesWS(new MovieContract.Model.OnListMovieListener() {
            @Override
            public void onResolve(ArrayList<Movie> movies) {
                view.onSuccess(movies);
            }
            @Override
            public void onReject(String message) {
                view.onFailure("Problemas al traer los datos desde el Presenter");
            }
        });

    }


}
