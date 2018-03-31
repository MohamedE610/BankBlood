
package com.example.bankblood.Models.Region;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data data;
    private final static long serialVersionUID = 2067105775610001975L;

}
