package com.androidavanzado.retrof_movies.movies.listMovies.genreList.presenter;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.model.GenreListModel;

import java.util.ArrayList;

public class GenreListPresenter implements GenreListContract.Presenter {
    private GenreListModel genreListModel;
    private GenreListContract.View view;

    public GenreListPresenter(GenreListContract.View view){
        this.view = view;
        genreListModel = new GenreListModel();
    }


    @Override
    public void getGenreList() {
        if (view != null){
            view.showProgress();
        }
        genreListModel.getMoviesByGenreWS(new GenreListContract.Model.OnGenreListListener() {
            @Override
            public void onResolve(ArrayList<Movie> genreMovies) {
                view.onSuccess(genreMovies);
                if (genreMovies != null){
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
