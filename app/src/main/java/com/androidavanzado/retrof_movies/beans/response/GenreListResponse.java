package com.androidavanzado.retrof_movies.beans.response;

import com.androidavanzado.retrof_movies.beans.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GenreListResponse {
    @SerializedName("genres")
    private ArrayList<Genre> genres = null;

    public ArrayList<Genre> getResults() {
        return genres;
    }

    public void setResults(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
