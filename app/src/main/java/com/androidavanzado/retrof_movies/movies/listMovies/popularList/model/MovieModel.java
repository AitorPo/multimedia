package com.androidavanzado.retrof_movies.movies.listMovies.popularList.model;

import android.util.Log;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.beans.response.MovieListResponse;
import com.androidavanzado.retrof_movies.movies.listMovies.popularList.contract.MovieContract;
import com.androidavanzado.retrof_movies.service.ApiClient;
import com.androidavanzado.retrof_movies.service.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.retrof_movies.utils.Constants.API_KEY;
import static com.androidavanzado.retrof_movies.utils.Constants.LANGUAGE;

public class MovieModel implements MovieContract.Model {
    private final String TAG = "MovieModel";


    @Override
    public void getMoviesWS(final OnListMovieListener onListMovieListener, int page) {
        ApiInterface apiInterface = ApiClient.buildClient()
                .create(ApiInterface.class);

        Call<MovieListResponse> call = apiInterface.getPopularMovies(API_KEY, LANGUAGE, page);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                ArrayList<Movie> movies = response.body().getResults();
                Log.d(TAG, "Lista cargada");
                onListMovieListener.onResolve(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListMovieListener.onReject(t);
            }
        });
    }


}
