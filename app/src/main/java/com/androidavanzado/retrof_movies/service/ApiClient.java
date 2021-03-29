package com.androidavanzado.retrof_movies.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.androidavanzado.retrof_movies.utils.Constants.BASE_URL;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit buildClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
