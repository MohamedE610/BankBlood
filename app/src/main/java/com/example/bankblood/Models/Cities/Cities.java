
package com.example.bankblood.Models.Cities;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cities implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    private final static long serialVersionUID = 1742001293179456437L;

}
