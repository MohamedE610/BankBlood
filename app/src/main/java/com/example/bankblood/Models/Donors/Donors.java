
package com.example.bankblood.Models.Donors;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donors implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<DonorData> data = null;
    private final static long serialVersionUID = -192547757537046914L;

}
