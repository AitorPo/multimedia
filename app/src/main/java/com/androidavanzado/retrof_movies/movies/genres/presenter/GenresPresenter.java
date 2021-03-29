package com.androidavanzado.retrof_movies.movies.genres.presenter;

import com.androidavanzado.retrof_movies.beans.Genre;
import com.androidavanzado.retrof_movies.movies.genres.contract.GenresContract;
import com.androidavanzado.retrof_movies.movies.genres.model.GenresModel;

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
        if (view != null){
            view.showProgress();
        }
        genresModel.getDetailsWS(new GenresContract.Model.OnGenresMovieListener() {
            @Override
            public void onResolve(ArrayList<Genre> genres) {
                view.onSuccess(genres);
                if (genres != null){
                    view.hideProgress();
                }
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
