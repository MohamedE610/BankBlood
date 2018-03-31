
package com.example.bankblood.Models.BloodTypeFilter;

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
    @SerializedName("donners")
    @Expose
    public Donners donners;
    private final static long serialVersionUID = -2227523197466634053L;

}
