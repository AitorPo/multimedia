package com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.presenter;

import com.androidavanzado.asynctasktestmovies_v2.beans.DetailsMovie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.contract.DetailsMovieContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.model.DetailsMovieModel;

import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsMoviePresenter implements DetailsMovieContract.Presenter {
    private DetailsMovieModel detailsMovieModel;
    private DetailsMovieContract.View view;

    public DetailsMoviePresenter(DetailsMovieContract.View view){
        this.view = view;
        detailsMovieModel = new DetailsMovieModel();
    }

    @Override
    public void getDetails() {
        detailsMovieModel.getDetailsWS(new DetailsMovieContract.Model.OnDetailsMovieListener() {

            @Override
            public void onResolve(ArrayList<DetailsMovie> details) {view.onSuccess(details);

            }

            @Override
            public void onReject(String message) { view.onFailure("Problemas al traer los datos desde el Presenter"); }
        });
    }
}
