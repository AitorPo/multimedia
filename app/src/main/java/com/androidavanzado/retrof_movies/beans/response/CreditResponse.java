package com.androidavanzado.retrof_movies.beans.response;

import com.androidavanzado.retrof_movies.beans.Cast;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Esta clase recoge el JSON "credits" de la consulta a la API
 * https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=language&append_to_response=response
 */
public class CreditResponse {
    @SerializedName("cast")
    private List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
