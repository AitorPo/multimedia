package com.androidavanzado.retrof_movies.movies.genres.model;

import android.util.Log;

import com.androidavanzado.retrof_movies.beans.Genre;
import com.androidavanzado.retrof_movies.beans.response.GenreListResponse;
import com.androidavanzado.retrof_movies.movies.genres.contract.GenresContract;
import com.androidavanzado.retrof_movies.service.ApiClient;
import com.androidavanzado.retrof_movies.service.ApiInterface;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.retrof_movies.utils.Constants.API_KEY;
import static com.androidavanzado.retrof_movies.utils.Constants.LANGUAGE;

public class GenresModel implements GenresContract.Model {
    private final String TAG = "GenresModel";

    @Override
    public void getDetailsWS(OnGenresMovieListener onGenresMovieListener) {
        ApiInterface apiInterface = ApiClient.buildClient()
            .create(ApiInterface.class);

       Call<GenreListResponse> call = apiInterface.getGenres(API_KEY, LANGUAGE);
       call.enqueue(new Callback<GenreListResponse>() {
           @Override
           public void onResponse(Call<GenreListResponse> call, Response<GenreListResponse> response) {
               ArrayList<Genre> genres = response.body().getResults();
               Log.d(TAG, "Lista cargada");
               onGenresMovieListener.onResolve(genres);
           }

           @Override
           public void onFailure(Call<GenreListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onGenresMovieListener.onReject(t);
           }
       });
    }
}
