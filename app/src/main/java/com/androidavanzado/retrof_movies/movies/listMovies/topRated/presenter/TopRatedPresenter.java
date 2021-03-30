package com.androidavanzado.retrof_movies.movies.listMovies.topRated.presenter;

import android.content.Context;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.contract.TopRatedContract;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.model.TopRatedModel;

import java.util.ArrayList;

public class TopRatedPresenter implements TopRatedContract.Presenter, TopRatedContract.Model.OnTopRatedListener {
    private TopRatedModel topRatedModel;
    private TopRatedContract.View view;

    public TopRatedPresenter(TopRatedContract.View view){
        this.view = view;
        topRatedModel = new TopRatedModel();
    }

    @Override
    public void getTopRatedMovies(Context context) {
        topRatedModel.getTopRatedMoviesWS(this, 1);
    }

    @Override
    public void getMoreTopRatedMovies(Context context, int page) {
        topRatedModel.getTopRatedMoviesWS(this, page);
    }

    @Override
    public void onResolve(ArrayList<Movie> topRatedMovies) {
        view.onSuccess(topRatedMovies);
    }

    @Override
    public void onReject(Throwable throwable) {
        view.onFailure(throwable);
    }
}
