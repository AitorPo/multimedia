package com.androidavanzado.asynctasktestmovies_v2.movies.genres.contract;

import com.androidavanzado.asynctasktestmovies_v2.beans.DetailsMovie;
import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.contract.DetailsMovieContract;

import java.util.ArrayList;

public interface GenresContract {
    interface Model{
        void getDetailsWS(GenresContract.Model.OnGenresMovieListener onGenresMovieListener);
        //Gestión del Callback
        interface OnGenresMovieListener{
            void onResolve(ArrayList<Genre> genres);
            void onReject(String message);
        }
    }

    interface Presenter{
        void getGenres();
    }

    interface View{
        void onSuccess(ArrayList<Genre>genres);
        void onFailure(String message);
    }
}
