package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Donner.Donner;
import com.example.bankblood.Models.Donners.Donners;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class DonnerSearchRequest extends FetchData implements Callback<Donners> {


    HashMap hashMap;

    public DonnerSearchRequest(HashMap hashMap){

        this.hashMap=hashMap;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donners> donnerByID = apiInterface.donnerSearch(hashMap);
        donnerByID.enqueue(this);
    }

    @Override
    public void onResponse(Call<Donners> call, Response<Donners> response) {
        Donners body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Donners> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
