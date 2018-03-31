package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Messages.Messages;
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

public class GetIncomingMessagesRequest extends FetchData implements Callback<Messages> {


    int id;

    public GetIncomingMessagesRequest(int id){

        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Messages>  incomingMessages= apiInterface.getIncomingMessages(id);
        incomingMessages.enqueue(this);
    }

    @Override
    public void onResponse(Call<Messages> call, Response<Messages> response) {
        Messages body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Messages> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
