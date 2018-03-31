
package com.example.bankblood.Models.Donner;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("bloodType")
    @Expose
    public String bloodType;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("outgoingMessages")
    @Expose
    public OutgoingMessages outgoingMessages;
    @SerializedName("incomingMessages")
    @Expose
    public IncomingMessages incomingMessages;
    private final static long serialVersionUID = 3517549579700979884L;

}
