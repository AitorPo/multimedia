package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.model;

import android.os.AsyncTask;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.contract.MovieContract;
import com.androidavanzado.asynctasktestmovies_v2.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieModel implements MovieContract.Model {

    private static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=d9c4177bb1cc819d43088d25fbe2474c&language=es-ES";
    private ArrayList<Movie> movieArrayList;
    OnListMovieListener onListMovieListener;

    @Override
    public void getMoviesWS(OnListMovieListener onListMovieListener) {
        this.onListMovieListener = onListMovieListener;
        MoviesAsyncTask asyncTask = new MoviesAsyncTask();
        asyncTask.execute();
    }

    //Parte del AsyncTask que se comunicará con el WS/API
    class MoviesAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();
            JSONObject objectMovies = post.getServerDataGetObject(URL);
            try {
                JSONArray movieArray = objectMovies.getJSONArray("results");
                movieArrayList = Movie.getArrayListFromJSON(movieArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if (res) {
                if (movieArrayList != null && movieArrayList.size() > 0){
                    onListMovieListener.onResolve(movieArrayList);
                }
            } else {
                onListMovieListener.onReject("Error al traer datos del WS/API");
            }
        }
    }



}
