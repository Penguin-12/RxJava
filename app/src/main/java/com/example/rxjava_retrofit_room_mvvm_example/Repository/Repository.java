package com.example.rxjava_retrofit_room_mvvm_example.Repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.rxjava_retrofit_room_mvvm_example.Models.Joke;
import com.example.rxjava_retrofit_room_mvvm_example.Models.JokesApi;
import com.example.rxjava_retrofit_room_mvvm_example.Models.Value;
import com.example.rxjava_retrofit_room_mvvm_example.Room.AppDatatbase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    Application application;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppDatatbase appDatatbase;
    MutableLiveData<List<Value>> jokeListMutableLiveData = new MutableLiveData<>();

    public Repository(Application application) {
        this.application = application;
        appDatatbase = Room.databaseBuilder(application.getApplicationContext(), AppDatatbase.class, "hey").build();
        compositeDisposable.add(appDatatbase.jokeValueDao().getAllJokes().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Value>>() {
            @Override
            public void accept(List<Value> values) throws Exception {
                jokeListMutableLiveData.postValue(values);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("Faillllllll", throwable.getMessage());
            }
        }));
    }

    public void getNewJokesFromInternetAndSaveIntoDB() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(JokesApi.BASE_URl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        JokesApi jokesApi = retrofit.create(JokesApi.class);
        Observable<Joke> jokeObservable = jokesApi.getJoke();
        compositeDisposable.add(jokeObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Joke>() {
                    @Override
                    public void onNext(final Joke joke) {
                        Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                appDatatbase.jokeValueDao().emptyDatabse();
                                appDatatbase.jokeValueDao().insertJokes(joke.getValue());
                            }
                        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(application.getApplicationContext(), "Jokes Saved", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("Saving to DB Failed", e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Fetching Jokes Failed", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(application.getApplicationContext(), "Database Updated", Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }


    public MutableLiveData<List<Value>> getJokeListMutableLiveData() {
        return jokeListMutableLiveData;
    }

    public void clearDisposables() {
        compositeDisposable.clear();
    }

}
