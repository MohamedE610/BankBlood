package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.BloodTypeFilter.BloodTypeFilter;
import com.example.bankblood.Models.BloodTypes.BloodTypes;
import com.example.bankblood.Models.City.City;
import com.example.bankblood.Models.Donner.Donner;
import com.example.bankblood.Models.Region.Region;
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

public class FilterDonnersByBloodTypeRequest extends FetchData implements Callback<BloodTypeFilter> {


    int blood_id;
    public FilterDonnersByBloodTypeRequest(int id){
        blood_id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<BloodTypeFilter> call = apiInterface.filterDonnersByBloodType(blood_id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<BloodTypeFilter> call, Response<BloodTypeFilter> response) {
        BloodTypeFilter body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<BloodTypeFilter> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
