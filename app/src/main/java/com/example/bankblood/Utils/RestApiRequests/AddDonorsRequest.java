package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Donor.Donor;
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

public class AddDonorsRequest extends FetchData implements Callback<Donor> {



    HashMap donnersDetails;
    public AddDonorsRequest(HashMap hashMap){

        donnersDetails=hashMap;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donor> addDonner = apiInterface.addDonor(donnersDetails);
        addDonner.enqueue(this);
    }

    @Override
    public void onResponse(Call<Donor> call, Response<Donor> response) {
        Donor body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Donor> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
