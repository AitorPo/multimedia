package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.presenter;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.model.GenreListModel;

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
        genreListModel.getMoviesByGenreWS(new GenreListContract.Model.OnGenreListListener() {
            @Override
            public void onResolve(ArrayList<Movie> genreMovies) { view.onSuccess(genreMovies); }
            @Override
            public void onReject(String message) { view.onFailure("Problemas al traer los datos desde el Presenter"); }
        });
    }
}
