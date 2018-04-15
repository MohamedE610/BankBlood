package com.example.bankblood.Utils.RestApiRequests;

import android.util.Log;

import com.example.bankblood.Models.Donor.Donor;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class UpdateDonorRequest extends FetchData implements Callback<Donor> {


    int id;
    HashMap donnersDetails;
    public UpdateDonorRequest(HashMap hashMap, int id){
        donnersDetails=hashMap;
        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donor> call = apiInterface.UpdateDonor(id,donnersDetails);
        call.enqueue(this);
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
