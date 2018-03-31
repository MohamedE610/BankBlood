
package com.example.bankblood.Models.BloodTypes;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BloodTypes implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    private final static long serialVersionUID = -79461752058646746L;

}
