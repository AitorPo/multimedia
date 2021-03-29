package com.androidavanzado.retrof_movies.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {
    private static final String ID = "id";
    private static final String NAME = "name";

    public Genre(){}

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /*public static ArrayList<Genre> getArrayListFromJSON(JSONArray genreList){
        ArrayList<Genre> genreArrayList = null;
        //Comprobamos sobre el JSONArray que pasamos por parámetro
        if(genreList != null && genreList.length() > 0){
            //inicializamos la lista propia creada dentro del método
            genreArrayList = new ArrayList<Genre>();
        }
        //Recorremos el JSONArray que recibimos por parámetro
        for (int i = 0; i < genreList.length(); i ++){
            try {
                //Creamos un JSONObject que recoja los elementos
                //del JSONArray que pasamos por parámetro
                JSONObject jsonObject = genreList.getJSONObject(i);
                //Instanciamos un objeto Genre que se rellenará en cada iteración
                Genre genre = new Genre();
                genre.setId(jsonObject.getInt(ID));
                genre.setName(jsonObject.getString(NAME));
                //Añadimos los objetos Genre a la lista creada en el método
                genreArrayList.add(genre);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Devolvemos la lista propia que ha guardado los datos del JSONArray
        return genreArrayList;
    }*/

}
