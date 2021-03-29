package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.presenter;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.contract.TopRatedContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.model.TopRatedModel;

import java.util.ArrayList;

public class TopRatedPresenter implements TopRatedContract.Presenter {
    private TopRatedModel topRatedModel;
    private TopRatedContract.View view;

    public TopRatedPresenter(TopRatedContract.View view){
        this.view = view;
        topRatedModel = new TopRatedModel();

    }

    @Override
    public void getTopRatedMovies() {
        topRatedModel.getTopRatedMoviesWS(new TopRatedContract.Model.OnTopRatedListener() {
            @Override
            public void onResolve(ArrayList<Movie> topRatedMovies) { view.onSuccess(topRatedMovies); }
            @Override
            public void onReject(String message) { view.onFailure("Problemas al traer los datos desde el Presenter"); }
        });
    }
}
