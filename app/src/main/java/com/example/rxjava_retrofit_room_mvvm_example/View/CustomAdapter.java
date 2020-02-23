package com.example.rxjava_retrofit_room_mvvm_example.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava_retrofit_room_mvvm_example.Models.Value;
import com.example.rxjava_retrofit_room_mvvm_example.R;
import com.example.rxjava_retrofit_room_mvvm_example.ViewModel.ViewModel;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<Value> list;
    Context context;
    ViewModel viewModel;

    public CustomAdapter(List<Value> list, Context context, ViewModel viewModel) {
        this.list = list;
        this.context = context;
        this.viewModel = viewModel;
    }

    void updateData(List<Value> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getJoke());


//        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Completable.fromAction(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        appDatatbase.ValueDao().deleteNote(list.get(position));
//
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableCompletableObserver() {
//                    @Override
//                    public void onComplete() {
//                        Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("Failllll",e.getMessage());
//                    }
//                })      ;
//
//                return false;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            constraintLayout = itemView.findViewById(R.id.parent);
        }
    }
}
