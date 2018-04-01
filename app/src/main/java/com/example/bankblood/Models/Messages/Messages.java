
package com.example.bankblood.Models.Messages;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages implements Serializable
{

    @SerializedName("data")
    @Expose
    public List<MessageData> data = null;
    private final static long serialVersionUID = 7673202712968580049L;

}
