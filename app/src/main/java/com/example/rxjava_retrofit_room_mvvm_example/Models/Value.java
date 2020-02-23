
package com.example.rxjava_retrofit_room_mvvm_example.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Value {
    @PrimaryKey(autoGenerate = true)
    @Expose
    private Integer id;
    @ColumnInfo
    @Expose
    private String joke;
    @Ignore
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;

    public Value() {

    }

    @Ignore
    public Value(String joke) {
        this.joke = joke;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
