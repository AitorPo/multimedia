package com.androidavanzado.retrof_movies.beans;

import com.androidavanzado.retrof_movies.beans.response.CreditResponse;
import com.androidavanzado.retrof_movies.beans.response.VideoListResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailsMovie {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("vote_average")
    @Expose
    private double vote_average;
    @SerializedName("vote_count")
    @Expose
    private int vote_count;
    @SerializedName("original_language")
    @Expose
    private String original_language;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("credits")
    @Expose
    private CreditResponse creditResponse;

    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genres;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;




    public ArrayList<Genre> getGenres() {
        return genres;
    }
    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public double getVote_average() {
        return vote_average;
    }
    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
    public CreditResponse getCreditResponse() {
        return creditResponse;
    }
    public void setCreditResponse(CreditResponse creditResponse) {
        this.creditResponse = creditResponse;
    }
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
    public Double getPopularity() { return popularity; }
    public void setPopularity(Double popularity) { this.popularity = popularity; }
    public int getRuntime() { return runtime; }
    public void setRuntime(int runtime) { this.runtime = runtime; }
    public String getHomepage() { return homepage; }
    public void setHomepage(String homepage) { this.homepage = homepage; }
}
