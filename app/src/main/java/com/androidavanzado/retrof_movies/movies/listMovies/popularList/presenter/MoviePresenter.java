package com.androidavanzado.retrof_movies.movies.listMovies.popularList.presenter;

import android.content.Context;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.contract.MovieContract;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.model.MovieModel;

import java.util.ArrayList;

public class MoviePresenter implements MovieContract.Presenter, MovieContract.Model.OnListMovieListener {
    private MovieModel movieModel;
    private MovieContract.View view;

    public MoviePresenter(MovieContract.View view){
        this.view = view;
        movieModel = new MovieModel();
    }

    @Override
    public void getMovieList(Context context) {

        movieModel.getMoviesWS(this, 1);
    }

    @Override
    public void getMoreMovies(Context context, int page) {
        movieModel.getMoviesWS(this, page);
    }

    @Override
    public void onResolve(ArrayList<Movie> movies) {
        view.onSuccess(movies);
    }

    @Override
    public void onReject(Throwable throwable) {
        view.onFailure(throwable);
    }
}
