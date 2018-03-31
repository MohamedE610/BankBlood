
package com.example.bankblood.Models.BloodTypeFilter;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
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
    private final static long serialVersionUID = -4801894417285947838L;

}
