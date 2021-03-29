package com.androidavanzado.retrof_movies.movies.genres.contract;

import com.androidavanzado.retrof_movies.beans.Genre;

import java.util.ArrayList;

public interface GenresContract {
    interface Model{
        void getDetailsWS(final OnGenresMovieListener onGenresMovieListener);
        //Gestión del Callback
        interface OnGenresMovieListener{
            void onResolve(ArrayList<Genre> genres);
            void onReject(Throwable throwable);
        }
    }

    interface Presenter{
        void getGenres();
    }

    interface View{
        void showProgress();
        void hideProgress();
        void onSuccess(ArrayList<Genre>genres);
        void onFailure(Throwable throwable);
    }
}
