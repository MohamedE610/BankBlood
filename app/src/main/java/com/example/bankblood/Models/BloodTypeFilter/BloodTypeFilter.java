
package com.example.bankblood.Models.BloodTypeFilter;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BloodTypeFilter implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data data;
    private final static long serialVersionUID = -4457980771617363871L;

}
