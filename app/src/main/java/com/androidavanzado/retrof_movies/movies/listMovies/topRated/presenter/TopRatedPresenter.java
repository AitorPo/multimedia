package com.androidavanzado.retrof_movies.movies.listMovies.topRated.presenter;

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
    public void getTopRatedMovies() {
        if (view != null){
            view.showProgress();
        }
        topRatedModel.getTopRatedMoviesWS(this);
    }

    @Override
    public void onResolve(ArrayList<Movie> topRatedMovies) {
        if (topRatedMovies != null){
            view.hideProgress();
        }
        view.onSuccess(topRatedMovies);
    }

    @Override
    public void onReject(Throwable throwable) {
        if (view != null){
            view.hideProgress();
        }
        view.onFailure(throwable);
    }
}
