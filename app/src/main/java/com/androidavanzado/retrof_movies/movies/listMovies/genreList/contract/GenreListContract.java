package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.contract;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;

import java.util.ArrayList;

public interface GenreListContract {
    interface Model{
        void getMoviesByGenreWS(OnGenreListListener onGenreListListener);
        interface OnGenreListListener{
            void onResolve(ArrayList<Movie> genreMovies);
            void onReject(String message);
        }
    }

    interface Presenter{
        void getGenreList();
    }

    interface View{
        void onSuccess(ArrayList<Movie> genreMovies);
        void onFailure(String message);
    }

}
