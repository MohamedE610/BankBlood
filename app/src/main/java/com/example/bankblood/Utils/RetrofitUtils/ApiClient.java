package com.example.bankblood.Utils.RetrofitUtils;

import android.provider.SyncStateContract;

import com.example.bankblood.Utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abdallah on 12/18/2017.
 */

public class ApiClient {

    public static final String BASE_URL = Constants.apiBasicUrl;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
