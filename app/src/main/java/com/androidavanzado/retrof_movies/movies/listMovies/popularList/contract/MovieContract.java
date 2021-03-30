package com.androidavanzado.retrof_movies.movies.listMovies.popularList.contract;

import android.content.Context;

import com.androidavanzado.retrof_movies.beans.Movie;

import java.util.ArrayList;

public interface MovieContract {
    interface Model{
        //Callback hacia el Presenter
        void getMoviesWS(final OnListMovieListener onListMovieListener, int page);
        //Gestión de los resultados del Callback
        interface OnListMovieListener{
            void onResolve(ArrayList<Movie> movies);
            void onReject(Throwable throwable);
        }
    }
        //Comunicador entre el Model y el View
    interface Presenter{
        void getMovieList(Context context);
        void getMoreMovies(Context context, int page);
    }

    interface View{

        void onSuccess(ArrayList<Movie> movies);
        void onFailure(Throwable throwable);
    }
}
