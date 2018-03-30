package com.example.bankblood.Utils.RetrofitUtils;




import com.example.bankblood.Models.Cities.Cities;
import com.example.bankblood.Models.Donners.Donners;
import com.example.bankblood.Models.Message.Message;
import com.example.bankblood.Models.Regions.Regions;
import com.example.bankblood.Utils.Constants;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/regions")
    Call<Regions> fetchRegions();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/cities")
    Call<Cities> fetchCities();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners")
    Call<Donners> fetchDonners();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("/donners")
    Call<HashMap> addDonner(@Body HashMap hashMap);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners/{donner_id}")
    Call<HashMap> getDonnerByID(@Path("donner_id") int donner_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners/{donner_id}/outgoingMessages")
    Call<HashMap> getOutgoingMessages(@Path("donner_id") int donner_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners/{donner_id}/incomingMessages")
    Call<HashMap> getIncomingMessages(@Path("donner_id") int donner_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners/{donner_id}/donate")
    Call<HashMap> addDonation(@Path("donner_id") int donner_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners/{donner_id}/donated")
    Call<HashMap> donationApprroved(@Path("donner_id") int donner_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/donners/{donner_id}/cancelDonation")
    Call<HashMap> cancelDonation(@Path("donner_id") int donner_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("/donners/{donner_id}/delete")
    Call<HashMap> deleteDonners(@Path("donner_id") int donner_id);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("/inbox/newMessage")
    Call<HashMap> newMessage(@Body HashMap hashMap);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/inbox/{inbox_id}")
    Call<Message> getMessageByID(@Path("inbox_id") int inbox_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("/donners/{inbox_id}/delete")
    Call<HashMap> markMessageAsRead(@Path("donner_id") int inbox_id);
}
