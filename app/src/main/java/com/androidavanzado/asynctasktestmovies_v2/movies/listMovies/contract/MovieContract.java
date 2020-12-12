package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.contract;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;

import java.util.ArrayList;

public interface MovieContract {
    interface Model{
        //Callback hacia el Presenter
        void getMoviesWS(OnListMovieListener onListMovieListener);
        //Gestión de los resultados del Callback
        interface OnListMovieListener{
            void onResolve(ArrayList<Movie> movies);
            void onReject(String message);
        }
    }
        //Comunicador entre el Model y el View
    interface Presenter{
        void getMovieList();
    }

    interface View{
        void onSuccess(ArrayList<Movie> movies);
        void onFailure(String message);
    }
}
