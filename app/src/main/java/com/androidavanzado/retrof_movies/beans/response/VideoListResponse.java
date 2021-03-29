package com.androidavanzado.retrof_movies.beans.response;

import com.androidavanzado.retrof_movies.beans.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class VideoListResponse {
    @SerializedName("results")
    private ArrayList<Video> results = null;

    public ArrayList<Video> getResults() {
        return results;
    }
    public void setResults(ArrayList<Video> results) {
        this.results = results;
    }
}
