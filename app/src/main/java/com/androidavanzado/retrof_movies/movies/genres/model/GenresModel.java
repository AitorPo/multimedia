package com.androidavanzado.asynctasktestmovies_v2.movies.genres.model;

import android.os.AsyncTask;

import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.contract.GenresContract;
import com.androidavanzado.asynctasktestmovies_v2.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenresModel implements GenresContract.Model {
    private static final String URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=e61e68e7e4858d661a6479587ff29ec2&language=es-ES";
    private ArrayList<Genre> genreArrayList;
    OnGenresMovieListener onGenresMovieListener;

    @Override
    public void getDetailsWS(OnGenresMovieListener onGenresMovieListener) {
        this.onGenresMovieListener = onGenresMovieListener;
        GenreAsyncTask genreAsyncTask = new GenreAsyncTask();
        genreAsyncTask.execute();
    }

    class GenreAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();
            JSONObject genreObject = post.getServerDataGetObject(URL);
            try {
                JSONArray genreJSONArray = genreObject.getJSONArray("genres");
                genreArrayList = Genre.getArrayListFromJSON(genreJSONArray);
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if (res){
                if (genreArrayList != null && genreArrayList.size() > 0){
                    onGenresMovieListener.onResolve(genreArrayList);
                }
            } else {
                onGenresMovieListener.onReject("Error al traer los datos del WS/API");
            }
        }
    }
}
