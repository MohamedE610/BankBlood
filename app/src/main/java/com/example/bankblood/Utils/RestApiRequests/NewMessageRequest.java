package com.example.bankblood.Utils.RestApiRequests;

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

public class NewMessageRequest extends FetchData implements Callback<HashMap> {
    HashMap messageDetails;
    public NewMessageRequest(HashMap messageDetails){

        this.messageDetails=messageDetails;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<HashMap>  newMessage= apiInterface.newMessage(messageDetails);
        newMessage.enqueue(this);
    }

    @Override
    public void onResponse(Call<HashMap> call, Response<HashMap> response) {
        HashMap body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<HashMap> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
