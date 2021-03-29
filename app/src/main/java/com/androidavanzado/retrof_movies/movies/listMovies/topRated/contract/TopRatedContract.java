package com.androidavanzado.retrof_movies.movies.listMovies.topRated.contract;

import com.androidavanzado.retrof_movies.beans.Movie;

import java.util.ArrayList;

public interface TopRatedContract {
    interface Model{
        void getTopRatedMoviesWS(final OnTopRatedListener onTopRatedListener);
        interface OnTopRatedListener{
            void onResolve(ArrayList<Movie> topRatedMovies);
            void onReject(Throwable throwable);
        }
    }

    interface Presenter{
        void getTopRatedMovies();
    }

    interface View{
        void showEmptyView();
        void hideEmptyView();
        void showProgress();
        void hideProgress();
        void onSuccess(ArrayList<Movie> topRatedMovies);
        void onFailure(Throwable throwable);
    }
}
