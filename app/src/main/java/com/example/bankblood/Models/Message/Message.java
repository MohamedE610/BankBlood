
package com.example.bankblood.Models.Message;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message implements Serializable
{

    @SerializedName("data")
    @Expose
    public Data data;
    private final static long serialVersionUID = -4639790502019734414L;

}
