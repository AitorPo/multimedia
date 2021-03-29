package com.androidavanzado.retrof_movies.service;

import com.androidavanzado.retrof_movies.beans.DetailsMovie;
import com.androidavanzado.retrof_movies.beans.Video;
import com.androidavanzado.retrof_movies.beans.response.GenreListResponse;
import com.androidavanzado.retrof_movies.beans.Movie;
import com.androidavanzado.retrof_movies.beans.response.MovieListResponse;
import com.androidavanzado.retrof_movies.beans.response.VideoListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/top_rated")
    Call<MovieListResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("discover/movie")
    Call<MovieListResponse> getMoviesByGenre(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("include_adult") boolean includeAdult,
            @Query("include_video") boolean includeVideo,
            @Query("with_genres") int id);

    @GET("genre/movie/list")
    Call<GenreListResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/{movie_id}/videos")
    Call<VideoListResponse> getMovieVideos(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String languageEn);

    @GET("movie/{movie_id}")
    Call<DetailsMovie> getMovieDetail(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            // Añadimos el JSON credits que contiene el array cast a la información de la película
            @Query("append_to_response") String credits);
}
