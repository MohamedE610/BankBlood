
package com.example.bankblood.Models.Donner;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donner implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data data;
    private final static long serialVersionUID = -134472876491607272L;

}
