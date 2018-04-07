package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Donor.Donor;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class GetDonorByIDRequest extends FetchData implements Callback<Donor> {


    int id;

    public GetDonorByIDRequest(int id){

        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Donor> donnerByID = apiInterface.getDonorByID(id);
        donnerByID.enqueue(this);
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
