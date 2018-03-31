
package com.example.bankblood.Models.City;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data_ data;
    private final static long serialVersionUID = 4031364313974902362L;

}
