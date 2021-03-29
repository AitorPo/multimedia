package com.androidavanzado.retrof_movies.beans;

import com.androidavanzado.retrof_movies.beans.response.CreditResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("credits")
    @Expose
    private CreditResponse creditResponse;

    public CreditResponse getCreditResponse() {
        return creditResponse;
    }

    public void setCreditResponse(CreditResponse creditResponse) {
        this.creditResponse = creditResponse;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getPoster_path() {
        return posterPath;
    }
    public void setPoster_path(String poster_path) {
        this.posterPath = poster_path;
    }

    /*public static ArrayList<Movie> getArrayListFromJSON(JSONArray movieList){
        ArrayList<Movie> movieArrayList = null;
        //Comprobamos sobre el JSONArray que pasamos por parámetro
        if(movieList != null && movieList.length() > 0){
            //inicializamos la lista propia creada dentro del método
            movieArrayList = new ArrayList<Movie>();
        }
        //Recorremos el JSONArray que recibimos por parámetro
        for (int i = 0; i < movieList.length(); i ++){
            try {
                //Creamos un JSONObject que recoja los elementos
                //del JSONArray que pasamos por parámetro
                JSONObject jsonObject = movieList.getJSONObject(i);
                //Instanciamos un objeto Movie que se rellenará en cada iteración
                Movie movie = new Movie();

                movie.setId(jsonObject.getInt(ID));
                movie.setTitle(jsonObject.getString(TITLE));
                movie.setVoteAverage(jsonObject.getDouble(VOTE_AVERAGE));
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
    }*/
}
