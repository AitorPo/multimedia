package com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.model;

import android.os.AsyncTask;

import com.androidavanzado.asynctasktestmovies_v2.beans.Genre;
import com.androidavanzado.asynctasktestmovies_v2.beans.Movie;
import com.androidavanzado.asynctasktestmovies_v2.movies.genres.view.GenresActivity;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.contract.GenreListContract;
import com.androidavanzado.asynctasktestmovies_v2.movies.listMovies.genreList.view.GenreListActivity;
import com.androidavanzado.asynctasktestmovies_v2.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenreListModel implements GenreListContract.Model {

    int idGenre = GenresActivity.idGenre;
    private ArrayList<Movie> genreListArrayList;
    OnGenreListListener onGenreListListener;
    @Override
    public void getMoviesByGenreWS(OnGenreListListener onGenreListListener) {
        this.onGenreListListener = onGenreListListener;
        GenreListAsyncTask genreListAsyncTask = new GenreListAsyncTask();
        genreListAsyncTask.execute();
    }

    class GenreListAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            String URL = "https://api.themoviedb.org/3/discover/movie" +
                    "?api_key=e61e68e7e4858d661a6479587ff29ec2" +
                    "&language=es-ES" +
                    "&sort_by=popularity.desc" +
                    "&include_adult=false" +
                    "&include_video=false" +
                    "&with_genres=" + idGenre;
            Post post = new Post();
            JSONObject objectGenreList = post.getServerDataGetObject(URL);
            try {
                JSONArray jsonGenreArray = objectGenreList.getJSONArray("results");
                genreListArrayList = Movie.getArrayListFromJSON(jsonGenreArray);
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if (res){
                if (genreListArrayList != null && genreListArrayList.size() > 0){
                    onGenreListListener.onResolve(genreListArrayList);
                }
            } else {
                onGenreListListener.onReject("Error al traer los datos del WS/API");
            }
        }
    }
}
