package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.model;

import android.os.AsyncTask;

import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.topRated.contract.TopRatedContract;
import com.androidavanzado.asynctasktestmovies_v2.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopRatedModel implements TopRatedContract.Model {

    private static final String URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=e61e68e7e4858d661a6479587ff29ec2&language=es-ES";
    private ArrayList<Movie> topRatedMovies;
    OnTopRatedListener onTopRatedListener;

    @Override
    public void getTopRatedMoviesWS(OnTopRatedListener onTopRatedListener) {
        this.onTopRatedListener = onTopRatedListener;
        TopRatedAsyncTask topRatedAsyncTask = new TopRatedAsyncTask();
        topRatedAsyncTask.execute();
    }

    class TopRatedAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();
            JSONObject topRatedObject = post.getServerDataGetObject(URL);
            try {
                JSONArray topRatedArray = topRatedObject.getJSONArray("results");
                topRatedMovies = Movie.getArrayListFromJSON(topRatedArray);
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if (res){
                if (topRatedMovies != null && topRatedMovies.size() > 0){
                    onTopRatedListener.onResolve(topRatedMovies);
                }
            } else {
                onTopRatedListener.onReject("Error al traer los datos del WS/API");
            }
        }
    }
}
