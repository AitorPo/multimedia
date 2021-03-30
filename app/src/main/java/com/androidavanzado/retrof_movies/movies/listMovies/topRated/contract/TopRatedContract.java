package com.androidavanzado.retrof_movies.movies.listMovies.topRated.contract;

import android.content.Context;

import com.androidavanzado.retrof_movies.beans.Movie;

import java.util.ArrayList;

public interface TopRatedContract {
    interface Model{
        void getTopRatedMoviesWS(final OnTopRatedListener onTopRatedListener, int page);
        interface OnTopRatedListener{
            void onResolve(ArrayList<Movie> topRatedMovies);
            void onReject(Throwable throwable);
        }
    }

    interface Presenter{
        void getTopRatedMovies(Context context);
        void getMoreTopRatedMovies(Context context, int page);
    }

    interface View{

        void onSuccess(ArrayList<Movie> topRatedMovies);
        void onFailure(Throwable throwable);
    }
}
