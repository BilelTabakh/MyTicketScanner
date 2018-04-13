package com.myticketscanner;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bilel Tabakh
 */

public class RetrofitClient {

    // Singleton Retrofit
    private static Retrofit retrofit = null;
    private RetrofitClient(){}

    public static Retrofit getClient() {
        if (retrofit == null) {

            final String BASE_URL = "http://testticketscanner.000webhostapp.com/";
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // Setting the log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}