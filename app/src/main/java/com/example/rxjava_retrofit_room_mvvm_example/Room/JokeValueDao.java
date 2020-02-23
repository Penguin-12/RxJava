package com.example.rxjava_retrofit_room_mvvm_example.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rxjava_retrofit_room_mvvm_example.Models.Value;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface JokeValueDao {


    @Query("Select * from Value")
    Flowable<List<Value>> getAllJokes();


    @Insert
    void insertJokes(List<Value> list);

    @Update
    void updateDatabase(Value... Values);

    @Delete
    void deleteNote(Value... Values);

    @Query("Delete from Value")
    void emptyDatabse();

    @Query("SELECT * FROM Value WHERE id = :number")
    public Value getNoteWithId(String number);
}
