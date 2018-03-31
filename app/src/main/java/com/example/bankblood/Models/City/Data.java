
package com.example.bankblood.Models.City;

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
    @SerializedName("region")
    @Expose
    public Region region;
    private final static long serialVersionUID = 7751136243726609329L;

}
