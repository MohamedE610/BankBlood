
package com.example.bankblood.Models.Region;

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
    @SerializedName("cities")
    @Expose
    public Cities cities;
    private final static long serialVersionUID = -3467085534550908375L;

}
