
package com.example.bankblood.Models.Messages;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageData implements Serializable
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
    public From from;
    @SerializedName("to")
    @Expose
    public To to;
    @SerializedName("read")
    @Expose
    public Boolean read;
    private final static long serialVersionUID = -2734589295894332564L;

}
