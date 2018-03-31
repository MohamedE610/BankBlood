package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.City.City;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class GetCityByIDRequest extends FetchData implements Callback<City> {


    int id;
    public GetCityByIDRequest(int id){
        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<City> call = apiInterface.getCityByID(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<City> call, Response<City> response) {
        City body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<City> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
