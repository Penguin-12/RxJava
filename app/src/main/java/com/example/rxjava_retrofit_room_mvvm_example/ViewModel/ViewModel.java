package com.example.rxjava_retrofit_room_mvvm_example.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rxjava_retrofit_room_mvvm_example.Models.Value;
import com.example.rxjava_retrofit_room_mvvm_example.Repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Value>> getAllJokes() {
        return repository.getJokeListMutableLiveData();
    }

    public void getJokesFromInternetAndSaveIntoDB() {
        repository.getNewJokesFromInternetAndSaveIntoDB();
    }

    public void clearDisposables() {
        repository.clearDisposables();
    }

}
