
package com.example.bankblood.Models.Regions;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("name")
    @Expose
    public String name;
    private final static long serialVersionUID = 4065690005773059826L;

}
