package com.androidavanzado.retrof_movies.movies.listMovies.genreList.contract;

import com.androidavanzado.retrof_movies.beans.Movie;

import java.util.ArrayList;

public interface GenreListContract {
    interface Model{
        void getMoviesByGenreWS(final OnGenreListListener onGenreListListener);
        interface OnGenreListListener{
            void onResolve(ArrayList<Movie> genreMovies);
            void onReject(Throwable throwable);
        }
    }

    interface Presenter{
        void getGenreList();
    }

    interface View{
        void onSuccess(ArrayList<Movie> genreMovies);
        void onFailure(Throwable throwable);
    }
}
