package com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.contract;

import com.androidavanzado.asynctasktestmovies_v2.beans.DetailsMovie;

import java.util.ArrayList;
import java.util.List;

public interface DetailsMovieContract {
    interface Model{
        void getDetailsWS(OnDetailsMovieListener onDetailsMovieListener);
        //Gestión del Callback
        interface OnDetailsMovieListener{
            void onResolve(ArrayList<DetailsMovie>details);
            void onReject(String message);
        }
    }

    interface Presenter{
        void getDetails();
    }

    interface View{
        void onSuccess(ArrayList<DetailsMovie>details);
        void onFailure(String message);
    }
}
