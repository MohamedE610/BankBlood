package com.example.bankblood.Utils.RetrofitUtils;




import com.example.bankblood.Models.BloodTypeFilter.BloodTypeFilter;
import com.example.bankblood.Models.BloodTypes.BloodTypes;
import com.example.bankblood.Models.Cities.Cities;
import com.example.bankblood.Models.City.City;
import com.example.bankblood.Models.Donor.Donor;
import com.example.bankblood.Models.Donors.Donors;
import com.example.bankblood.Models.Message.Message;
import com.example.bankblood.Models.Messages.Messages;
import com.example.bankblood.Models.Region.Region;
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
    @POST("donors/search")
    Call<Donors> donorSearch(@Body HashMap hashMap);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/fb/{firebaseId}")
    Call<Donor> getDonorByFirebaseID(@Path("firebaseId") String firebaseId);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("donors/{donor_id}")
    Call<Donor> UpdateDonor(@Path("donor_id") int donor_id,@Body HashMap HASH_MAP);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("regions/{region_id}")
    Call<Region> getRegionByID(@Path("region_id") int region_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("cities/{city_id}")
    Call<City> getCityByID(@Path("city_id") int city_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("bloodtypes")
    Call<BloodTypes> fetchBloodTypes();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("bloodtypes/{blood_id}")
    Call<BloodTypeFilter> filterDonorsByBloodType(@Path("blood_id")int blood_id);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("regions")
    Call<Regions> fetchRegions();



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("cities")
    Call<Cities> fetchCities();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors")
    Call<Donors> fetchDonors();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("donors")
    Call<Donor> addDonor(@Body HashMap hashMap);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/{donor_id}")
    Call<Donor> getDonorByID(@Path("donor_id") int donor_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/{donor_id}/outgoingMessages")
    Call<Messages> getOutgoingMessages(@Path("donor_id") int donor_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/{donor_id}/incomingMessages")
    Call<Messages> getIncomingMessages(@Path("donor_id") int donor_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/{donor_id}/donate")
    Call<HashMap> addDonation(@Path("donor_id") int donor_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/{donor_id}/donated")
    Call<HashMap> donationApprroved(@Path("donor_id") int donor_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("donors/{donor_id}/cancelDonation")
    Call<HashMap> cancelDonation(@Path("donor_id") int donor_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("donors/{donor_id}/delete")
    Call<HashMap> deleteDonors(@Path("donor_id") int donor_id);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("inbox/newMessage")
    Call<HashMap> newMessage(@Body HashMap hashMap);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("inbox/{inbox_id}")
    Call<Message> getMessageByID(@Path("inbox_id") int inbox_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("inbox/{inbox_id}/read")
    Call<Message> markMessageAsRead(@Path("inbox_id") int inbox_id);
}
