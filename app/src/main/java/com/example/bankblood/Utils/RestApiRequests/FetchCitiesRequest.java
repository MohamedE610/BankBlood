package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Cities.Cities;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class FetchCitiesRequest extends FetchData implements Callback<Cities> {



    public FetchCitiesRequest(){

    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Cities>   citiesCall = apiInterface.fetchCities();
        citiesCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Cities> call, Response<Cities> response) {
        Cities body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Cities> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
