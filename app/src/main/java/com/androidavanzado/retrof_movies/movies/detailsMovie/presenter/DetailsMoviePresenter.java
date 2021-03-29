package com.androidavanzado.retrof_movies.movies.detailsMovie.presenter;

import com.androidavanzado.retrof_movies.beans.DetailsMovie;
import com.androidavanzado.retrof_movies.beans.Video;
import com.androidavanzado.retrof_movies.beans.response.VideoListResponse;
import com.androidavanzado.retrof_movies.movies.detailsMovie.contract.DetailsMovieContract;
import com.androidavanzado.retrof_movies.movies.detailsMovie.model.DetailsMovieModel;

import java.util.ArrayList;

public class DetailsMoviePresenter implements DetailsMovieContract.Presenter {
    private DetailsMovieModel detailsMovieModel;
    private DetailsMovieContract.View view;

    public DetailsMoviePresenter(DetailsMovieContract.View view){
        this.view = view;
        detailsMovieModel = new DetailsMovieModel();
    }

    @Override
    public void getDetails(int movieId) {
        detailsMovieModel.getDetailsWS(new DetailsMovieContract.Model.OnDetailsMovieListener() {

            @Override
            public void onResolve(DetailsMovie detailsMovie) {
                view.onSuccess(detailsMovie);
                if (detailsMovie != null){
                    getTrailers(movieId);
                    view.hideProgress();
                }
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, movieId);
    }

    private void getTrailers(int movieId){
        detailsMovieModel.getVideosWS(new DetailsMovieContract.Model.OnGetVideosListener() {
            @Override
            public void onResolve(ArrayList<Video> videos) {
                view.onSuccessTrailers(videos);
            }

            @Override
            public void onReject(Throwable throwable) {

            }
        }, movieId);
    }
}
