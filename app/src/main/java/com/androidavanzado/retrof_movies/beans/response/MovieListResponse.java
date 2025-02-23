package com.androidavanzado.retrof_movies.beans.response;

import com.androidavanzado.retrof_movies.beans.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieListResponse {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private ArrayList<Movie> results;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public ArrayList<Movie> getResults() {
        return results;
    }
    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
