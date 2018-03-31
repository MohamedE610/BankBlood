
package com.example.bankblood.Models.City;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data data;
    private final static long serialVersionUID = 7091349786765083450L;

}
