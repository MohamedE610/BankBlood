package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Regions.Regions;
import com.example.bankblood.Utils.Callbacks;
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

public class FetchRegionsRequest extends FetchData implements Callback<Regions> {



    public FetchRegionsRequest(){

    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Regions>  regionsCall = apiInterface.fetchRegions();
        regionsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Regions> call, Response<Regions> response) {
        Regions body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Regions> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
