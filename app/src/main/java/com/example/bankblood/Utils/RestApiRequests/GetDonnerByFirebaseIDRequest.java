package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Donner.Donner;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class GetDonnerByFirebaseIDRequest extends FetchData implements Callback<Donner> {


    String id;

    public GetDonnerByFirebaseIDRequest(String id){

        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donner> donnerByID = apiInterface.getDonnerByFirebaseID(id);
        donnerByID.enqueue(this);
    }

    @Override
    public void onResponse(Call<Donner> call, Response<Donner> response) {
        Donner body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Donner> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
