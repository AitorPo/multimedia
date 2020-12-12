package com.androidavanzado.asynctasktestmovies_v2.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String OVERVIEW = "overview";
    private static final String POSTER_PATH = "poster_path";
    private static final String VOTE_AVERAGE = "vote_average";

    public Movie(String title){
        this.title = title;
    }

    public Movie(){}

    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private String vote_average;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public String getVoteAverage() { return vote_average; }
    public void setVoteAverage(String vote_average) { this.vote_average = vote_average; }

    public static ArrayList<Movie> getArrayListFromJSON(JSONArray movieList){
        ArrayList<Movie> movieArrayList = null;
        //Comprobamos sobre el JSONArray que pasamos por parámetro
        if(movieList != null && movieList.length() > 0){
            //inicializamos la lista propia creada dentro del método
            movieArrayList = new ArrayList<Movie>();
        }
        //Recorremos el JSONArray que recibimos por parámetro
        for (int i = 0; i < movieList.length(); i ++){
            try {
                /*Creamos un JSONObject que recoja los elementos
                del JSONArray que pasamos por parámetro*/
                JSONObject jsonObject = movieList.getJSONObject(i);
                //Instanciamos un objeto Movie que se rellenará en cada iteración
                Movie movie = new Movie();

                movie.setId(jsonObject.getInt(ID));
                movie.setTitle(jsonObject.getString(TITLE));
                movie.setVoteAverage(jsonObject.getString(VOTE_AVERAGE));
                movie.setOverview(jsonObject.getString(OVERVIEW));
                movie.setPoster_path(jsonObject.getString(POSTER_PATH));
                //Añadimos los objetos Movie a la lista creada en el método
                movieArrayList.add(movie);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Devolvemos la lista propia que ha guardado los datos del JSONArray
        return movieArrayList;
    }
}
