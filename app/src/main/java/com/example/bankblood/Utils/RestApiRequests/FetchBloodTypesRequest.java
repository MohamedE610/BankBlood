package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.BloodTypes.BloodTypes;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class FetchBloodTypesRequest extends FetchData implements Callback<BloodTypes> {


    public FetchBloodTypesRequest(){
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<BloodTypes> call = apiInterface.fetchBloodTypes();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
        BloodTypes body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<BloodTypes> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
