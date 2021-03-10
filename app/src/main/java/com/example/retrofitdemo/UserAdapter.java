package com.example.retrofitdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitdemo.model.Data;
import com.example.retrofitdemo.databinding.UserRowBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    List<Data> dataList;
    Context ctx;

    public UserAdapter(List<Data> dataList, Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(ctx).inflate(R.layout.user_row, null));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
        Data data = dataList.get(position);
        holder.getBinding().setVariable(com.example.retrofitdemo.BR.user_data,data);
        holder.getBinding().setAvatar(data.getAvatar());
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder{
        private final UserRowBinding binding;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public UserRowBinding getBinding() {
            return binding;
        }
    }

    public void addData(List<Data> listOfData){
        for(Data data:listOfData){
            dataList.add(data);
        }
        notifyDataSetChanged();
    }
}
