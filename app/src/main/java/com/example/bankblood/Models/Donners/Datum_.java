
package com.example.bankblood.Models.Donners;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_ implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("from")
    @Expose
    public String from;
    @SerializedName("to")
    @Expose
    public String to;
    @SerializedName("read")
    @Expose
    public Boolean read;
    private final static long serialVersionUID = -1939886914162214684L;

}