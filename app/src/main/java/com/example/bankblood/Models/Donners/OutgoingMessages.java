
package com.example.bankblood.Models.Donners;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutgoingMessages implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<Datum_> data = null;
    private final static long serialVersionUID = -3529216079093787393L;

}
