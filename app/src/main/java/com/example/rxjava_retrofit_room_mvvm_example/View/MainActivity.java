package com.example.rxjava_retrofit_room_mvvm_example.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava_retrofit_room_mvvm_example.Models.Value;
import com.example.rxjava_retrofit_room_mvvm_example.R;
import com.example.rxjava_retrofit_room_mvvm_example.ViewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewModel viewModel;
    List<Value> jokeList;
    CustomAdapter customAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.clearDisposables();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Chuck Norris Jokes");
        jokeList = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllJokes().observe(this, new Observer<List<Value>>() {
            @Override
            public void onChanged(List<Value> values) {
                jokeList.clear();
                jokeList.addAll(values);
                customAdapter.notifyDataSetChanged();
            }
        });
        initRecylerView();
        viewModel.getJokesFromInternetAndSaveIntoDB();

    }

    public void initRecylerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(jokeList, this, viewModel);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
