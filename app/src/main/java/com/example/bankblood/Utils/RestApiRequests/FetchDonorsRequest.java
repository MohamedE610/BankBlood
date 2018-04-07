package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Donors.Donors;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class FetchDonorsRequest extends FetchData implements Callback<Donors> {



    public FetchDonorsRequest(){

    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donors> donnersCall = apiInterface.fetchDonors();
        donnersCall.enqueue(this);
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
