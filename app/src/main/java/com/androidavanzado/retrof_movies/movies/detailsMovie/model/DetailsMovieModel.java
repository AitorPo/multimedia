package com.androidavanzado.retrof_movies.movies.detailsMovie.model;

import android.util.Log;

import com.androidavanzado.retrof_movies.beans.DetailsMovie;
import com.androidavanzado.retrof_movies.beans.response.VideoListResponse;
import com.androidavanzado.retrof_movies.movies.detailsMovie.contract.DetailsMovieContract;
import com.androidavanzado.retrof_movies.service.ApiClient;
import com.androidavanzado.retrof_movies.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.retrof_movies.utils.Constants.API_KEY;
import static com.androidavanzado.retrof_movies.utils.Constants.APPEND_TO_RESPONSE_CAST;
import static com.androidavanzado.retrof_movies.utils.Constants.LANGUAGE;
import static com.androidavanzado.retrof_movies.utils.Constants.LANGUAGE_EN;

public class DetailsMovieModel implements DetailsMovieContract.Model {
    private final String TAG = "DetailsMovieModel";
    private String[] appends;
    //int idMovie = MovieListActivity.idMovie;

    @Override
    public void getDetailsWS(final OnDetailsMovieListener onDetailsMovieListener, int movieId) {
        ApiInterface apiInterface = ApiClient.buildClient()
                .create(ApiInterface.class);
        //appends = new String[]{APPEND_TO_RESPONSE_CAST, APPEND_TO_RESPONSE_VIDEOS};


        Call<DetailsMovie> call = apiInterface.getMovieDetail(movieId, API_KEY, LANGUAGE, APPEND_TO_RESPONSE_CAST);
        call.enqueue(new Callback<DetailsMovie>() {
            @Override
            public void onResponse(Call<DetailsMovie> call, Response<DetailsMovie> response) {
                DetailsMovie detailsMovie = response.body();
                Log.d(TAG, "Datos cargados");
                onDetailsMovieListener.onResolve(detailsMovie);
            }

            @Override
            public void onFailure(Call<DetailsMovie> call, Throwable t) {
                Log.e(TAG, t.toString());
                onDetailsMovieListener.onReject(t);
            }
        });



    }

    @Override
    public void getVideosWS(OnGetVideosListener onGetVideosListener, int movieId) {
        ApiInterface apiInterface = ApiClient.buildClient()
                .create(ApiInterface.class);

        Call<VideoListResponse> videosCall = apiInterface.getMovieVideos(movieId, API_KEY, LANGUAGE_EN);
        videosCall.enqueue(new Callback<VideoListResponse>() {
            @Override
            public void onResponse(Call<VideoListResponse> call, Response<VideoListResponse> response) {
                VideoListResponse videoListResponse = response.body();
                Log.d(TAG, "Videos cargados");
                onGetVideosListener.onResolve(videoListResponse.getResults());
            }

            @Override
            public void onFailure(Call<VideoListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onGetVideosListener.onReject(t);
            }
        });
    }

}
