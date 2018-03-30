package com.example.bankblood.Utils.RestApiRequests;

import com.example.bankblood.Models.Message.Message;
import com.example.bankblood.Utils.RetrofitUtils.ApiClient;
import com.example.bankblood.Utils.RetrofitUtils.ApiInterface;
import com.example.bankblood.Utils.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by E610 on 3/30/2018.
 */

public class getMessageByIDRequest extends FetchData implements Callback<Message> {
    int id;
    public getMessageByIDRequest(int id){

        this.id=id;
    }
    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Message>  newMessage= apiInterface.getMessageByID(id);
        newMessage.enqueue(this);
    }

    @Override
    public void onResponse(Call<Message> call, Response<Message> response) {
        Message body=response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Message> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
