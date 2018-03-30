
package com.example.bankblood.Models.Donners;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncomingMessages implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum__> data = null;
    private final static long serialVersionUID = -2435261189272977362L;

}
