
package com.example.bankblood.Models.BloodTypeFilter;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donners implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    private final static long serialVersionUID = 8961842032756040047L;

}
