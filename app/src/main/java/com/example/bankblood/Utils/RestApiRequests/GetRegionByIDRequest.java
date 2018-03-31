package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Region.Region;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class GetRegionByIDRequest extends FetchData implements Callback<Region> {


    int id;
    public GetRegionByIDRequest(int id){
        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Region> call = apiInterface.getRegionByID(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Region> call, Response<Region> response) {
        Region body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Region> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
