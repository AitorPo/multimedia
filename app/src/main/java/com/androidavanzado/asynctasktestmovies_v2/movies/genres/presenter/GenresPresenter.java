package com.androidavanzado.asynctasktestmovies_v2.movies.genres.presenter;

import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.contract.GenresContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.model.GenresModel;

import java.util.ArrayList;

public class GenresPresenter implements GenresContract.Presenter {
    private GenresModel genresModel;
    private GenresContract.View view;

    public GenresPresenter(GenresContract.View view){
        this.view = view;
        genresModel = new GenresModel();
    }

    @Override
    public void getGenres() {
        genresModel.getDetailsWS(new GenresContract.Model.OnGenresMovieListener() {
            @Override
            public void onResolve(ArrayList<Genre> genres) { view.onSuccess(genres); }
            @Override
            public void onReject(String message) { view.onFailure("Problemas al traer los datos delde el Presenter"); }
        });
    }
}
