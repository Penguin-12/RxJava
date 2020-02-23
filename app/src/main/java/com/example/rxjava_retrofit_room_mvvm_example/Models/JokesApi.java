package com.example.rxjava_retrofit_room_mvvm_example.Models;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokesApi {

    String BASE_URl = "http://api.icndb.com/jokes/";

    @GET("random/20")
    Observable<Joke> getJoke();

}
