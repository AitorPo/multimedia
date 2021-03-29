package com.androidavanzado.retrof_movies.movies.listMovies.genreList.model;

import android.util.Log;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.beans.response.MovieListResponse;
import com.androidavanzado.retrof_movies.movies.genres.view.GenresActivity;
import com.androidavanzado.retrof_movies.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.retrof_movies.service.ApiClient;
import com.androidavanzado.retrof_movies.service.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.retrof_movies.utils.Constants.API_KEY;
import static com.androidavanzado.retrof_movies.utils.Constants.INCLUDE_ADULT;
import static com.androidavanzado.retrof_movies.utils.Constants.INCLUDE_VIDEO;
import static com.androidavanzado.retrof_movies.utils.Constants.LANGUAGE;
import static com.androidavanzado.retrof_movies.utils.Constants.SORT_BY_POPULARITY_DESC;

public class GenreListModel implements GenreListContract.Model {
    private final String TAG = "GenreListModel";
    int idGenre = GenresActivity.idGenre;

    @Override
    public void getMoviesByGenreWS(final OnGenreListListener onGenreListListener) {
        ApiInterface apiInterface = ApiClient.buildClient()
                .create(ApiInterface.class);

        Call<MovieListResponse> call = apiInterface.getMoviesByGenre(API_KEY, LANGUAGE,
                SORT_BY_POPULARITY_DESC, INCLUDE_ADULT, INCLUDE_VIDEO, idGenre);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                ArrayList<Movie> movies = response.body().getResults();
                Log.d(TAG, "Lista cargada");
                onGenreListListener.onResolve(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onGenreListListener.onReject(t);
            }
        });
    }
}
