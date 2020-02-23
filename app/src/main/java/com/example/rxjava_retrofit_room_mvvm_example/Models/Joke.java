
package com.example.rxjava_retrofit_room_mvvm_example.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Joke {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private List<Value> value = null;

    public String getType() {
        return type;
    }


    public List<Value> getValue() {
        return value;
    }


}
