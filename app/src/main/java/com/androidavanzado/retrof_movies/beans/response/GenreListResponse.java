package com.androidavanzado.retrof_movies.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GenreListResponse {
    @SerializedName("genres")
    private ArrayList<Genre> results = null;

    public ArrayList<Genre> getResults() {
        return results;
    }

    public void setResults(ArrayList<Genre> results) {
        this.results = results;
    }
}
