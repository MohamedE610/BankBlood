package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Donors.Donors;
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

public class DonorSearchRequest extends FetchData implements Callback<Donors> {


    HashMap hashMap;

    public DonorSearchRequest(HashMap hashMap){

        this.hashMap=hashMap;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donors> donnerByID = apiInterface.donorSearch(hashMap);
        donnerByID.enqueue(this);
    }

    @Override
    public void onResponse(Call<Donors> call, Response<Donors> response) {
        Donors body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Donors> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
