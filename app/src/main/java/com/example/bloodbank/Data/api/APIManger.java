package com.example.bloodbank.Data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManger {

    private static final String BASE_URL = "http://ipda3-tech.com/blood-bank/api/v1/";
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory ( GsonConverterFactory.create () )
                    .build();

        }
        return retrofit;
    }

    public static WebService getApis(){
       return getInstance ().create ( WebService.class );
    }
}
