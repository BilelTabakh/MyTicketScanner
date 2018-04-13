package com.myticketscanner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bilel Tabakh
 */

interface Requests {
    // API To check the code
    @GET(".")
    Call<ResponseBody> checkCode(@Query("code") String code);
}
