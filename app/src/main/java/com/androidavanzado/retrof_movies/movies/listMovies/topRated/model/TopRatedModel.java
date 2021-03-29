package com.androidavanzado.retrof_movies.movies.listMovies.topRated.model;

import android.util.Log;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.beans.response.MovieListResponse;
import com.androidavanzado.retrof_movies.movies.listMovies.topRated.contract.TopRatedContract;
import com.androidavanzado.retrof_movies.service.ApiClient;
import com.androidavanzado.retrof_movies.service.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.retrof_movies.utils.Constants.API_KEY;
import static com.androidavanzado.retrof_movies.utils.Constants.LANGUAGE;

public class TopRatedModel implements TopRatedContract.Model {
    private final String TAG = "TopRatedModel";

    @Override
    public void getTopRatedMoviesWS(OnTopRatedListener onTopRatedListener) {
        ApiInterface apiInterface = ApiClient.buildClient()
                .create(ApiInterface.class);

        Call<MovieListResponse> call = apiInterface.getTopRatedMovies(API_KEY, LANGUAGE);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                ArrayList<Movie> topRatedMovies = response.body().getResults();
                Log.d(TAG, "TopRatedModel cargado");
                onTopRatedListener.onResolve(topRatedMovies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onTopRatedListener.onReject(t);
            }
        });
    }
}
