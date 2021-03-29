package com.androidavanzado.retrof_movies.movies.detailsMovie.contract;

import com.androidavanzado.retrof_movies.beans.DetailsMovie;
import com.androidavanzado.retrof_movies.beans.Video;

import java.util.ArrayList;

public interface DetailsMovieContract {
    interface Model{
        void getDetailsWS(final OnDetailsMovieListener onDetailsMovieListener, int movieId);
        void getVideosWS(final OnGetVideosListener onGetVideosListener, int movieId);
        //Gestión del Callback
        interface OnDetailsMovieListener{
            void onResolve(DetailsMovie detailsMovie);
            void onReject(Throwable throwable);
        }

        interface OnGetVideosListener{
            void onResolve(ArrayList<Video> videos);
            void onReject(Throwable throwable);
        }
    }

    interface Presenter{
        void getDetails(int movieId);

    }

    interface View{
        void showProgress();
        void hideProgress();
        void onSuccess(DetailsMovie detailsMovie);
        void onSuccessTrailers(ArrayList<Video> videos);
        void onFailure(Throwable throwable);
    }
}
