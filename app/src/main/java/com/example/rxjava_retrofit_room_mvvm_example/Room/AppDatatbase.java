package com.example.rxjava_retrofit_room_mvvm_example.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rxjava_retrofit_room_mvvm_example.Models.Value;


@Database(entities = {Value.class}, version = 1, exportSchema = false)

public abstract class AppDatatbase extends RoomDatabase {
    public abstract JokeValueDao jokeValueDao();
}
