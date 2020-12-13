package com.androidavanzado.asynctasktestmovies_v2.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailsMovie {
    private static final String ID = "id";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String TITLE = "title";
    private static final String OVERVIEW = "overview";
    private static final String POSTER_PATH = "poster_path";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String VOTE_COUNT = "vote_count";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String POPULARITY = "popularity";
    private static final String RUNTIME = "runtime";
    private static final String HOMEPAGE = "homepage";


    public DetailsMovie(String title){
        this.title = title;
    }

    public DetailsMovie(){}

    private int id;
    private String title;
    private String original_title;
    private String overview;
    private String poster_path;
    private double vote_average;
    private int vote_count;
    private String original_language;
    private int popularity;
    private int runtime;
    private String homepage;

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
    public String getOriginal_title(){ return original_title; }
    public void setOriginal_title(String original_title) { this.original_title = original_title; }
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
    public double getVoteAverage() { return vote_average; }
    public void setVoteAverage(double vote_average) { this.vote_average = vote_average; }
    public int getVote_count() { return vote_count; }
    public void setVote_count(int vote_count) { this.vote_count = vote_count; }
    public String getOriginal_language() { return original_language; }
    public void setOriginal_language(String original_language) { this.original_language = original_language; }
    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) { this.popularity = popularity; }
    public int getRuntime() { return runtime; }
    public void setRuntime(int runtime) { this.runtime = runtime; }
    public String getHomepage() { return homepage; }
    public void setHomepage(String homepage) { this.homepage = homepage; }

    public static ArrayList<DetailsMovie> getArrayListFromJSON(JSONArray movieList){
        ArrayList<DetailsMovie> detailsMovieArrayList = null;
        //Comprobamos sobre el JSONArray que pasamos por parámetro
        if(movieList != null && movieList.length() > 0){
            //inicializamos la lista propia creada dentro del método
            detailsMovieArrayList = new ArrayList<DetailsMovie>();
        }
        //Recorremos el JSONArray que recibimos por parámetro
        for (int i = 0; i < movieList.length(); i ++){
            try {
                /*Creamos un JSONObject que recoja los elementos
                del JSONArray que pasamos por parámetro*/
                JSONObject jsonObject = movieList.getJSONObject(i);
                //Instanciamos un objeto Movie que se rellenará en cada iteración
                DetailsMovie detailsMovie = new DetailsMovie();

                detailsMovie.setId(jsonObject.getInt(ID));
                detailsMovie.setTitle(jsonObject.getString(TITLE));
                detailsMovie.setOriginal_title(jsonObject.getString(ORIGINAL_TITLE));
                detailsMovie.setOverview(jsonObject.getString(OVERVIEW));
                detailsMovie.setPoster_path(jsonObject.getString(POSTER_PATH));
                detailsMovie.setVoteAverage(jsonObject.getDouble(VOTE_AVERAGE));
                detailsMovie.setVote_count(jsonObject.getInt(VOTE_COUNT));
                detailsMovie.setOriginal_language(jsonObject.getString(ORIGINAL_LANGUAGE));
                detailsMovie.setPopularity(jsonObject.getInt(POPULARITY));
                detailsMovie.setRuntime(jsonObject.getInt(RUNTIME));
                detailsMovie.setHomepage(jsonObject.getString(HOMEPAGE));
                //Añadimos los objetos Movie a la lista creada en el método
                detailsMovieArrayList.add(detailsMovie);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Devolvemos la lista propia que ha guardado los datos del JSONArray
        return detailsMovieArrayList;
    }
}
