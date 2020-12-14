package com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.model;

import android.content.Intent;
import android.os.AsyncTask;

import com.androidavanzado.asynctasktestmovies_v2.beans.DetailsMovie;
import com.androidavanzado.asynctasktestmovies_v2.movies.detailsMovie.contract.DetailsMovieContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view.GenreListActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.popularList.view.MovieListActivity;
import com.androidavanzado.asynctasktestmovies_v2.utils.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsMovieModel implements DetailsMovieContract.Model {

    int idMovie = MovieListActivity.idMovie;
    //private static String URL = "https://api.themoviedb.org/3/movie/" + idMovie + "?api_key=e61e68e7e4858d661a6479587ff29ec2&language=en-US&append_to_response=1";
    private ArrayList<DetailsMovie> detailsMoviesArrayList;
    OnDetailsMovieListener onDetailsMovieListener;
    @Override
    public void getDetailsWS(OnDetailsMovieListener onDetailsMovieListener) {
        this.onDetailsMovieListener = onDetailsMovieListener;
        DetailsMovieAsyncTask detailsMovieAsyncTask = new DetailsMovieAsyncTask();
        detailsMovieAsyncTask.execute();
    }

    class DetailsMovieAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {

            String URL = "https://api.themoviedb.org/3/movie/" + idMovie + "?api_key=e61e68e7e4858d661a6479587ff29ec2&language=es-ES&append_to_response=1";

            Post post = new Post();
            JSONObject objectDetailsMovie = post.getServerDataGetObject(URL);
            JSONArray jsonDetailsArray = new JSONArray();
            jsonDetailsArray.put(objectDetailsMovie);
            detailsMoviesArrayList = DetailsMovie.getArrayListFromJSON(jsonDetailsArray);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                if(detailsMoviesArrayList != null && detailsMoviesArrayList.size() > 0){
                    onDetailsMovieListener.onResolve(detailsMoviesArrayList);
                }
            } else{
                onDetailsMovieListener.onReject("Error al traer los datos del WS/API");
            }
        }
    }

}
