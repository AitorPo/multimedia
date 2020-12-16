package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.contract;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;

import java.util.ArrayList;

public interface TopRatedContract {
    interface Model{
        void getTopRatedMoviesWS(OnTopRatedListener onTopRatedListener);
        interface OnTopRatedListener{
            void onResolve(ArrayList<Movie> topRatedMovies);
            void onReject(String message);
        }
    }

    interface Presenter{
        void getTopRatedMovies();
    }

    interface View{
        void onSuccess(ArrayList<Movie> topRatedMovies);
        void onFailure(String message);
    }
}
