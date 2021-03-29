package com.androidavanzado.retrof_movies.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieListResponse {
    @SerializedName("results")
    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
