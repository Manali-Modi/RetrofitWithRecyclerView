package com.example.retrofitdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.Model.Data;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    List<Data> dataList;
    Context ctx;
    String userId, email, firstName, lastName, avatar;

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
        holder.txtName.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
        holder.txtEmailId.setText(dataList.get(position).getEmail() + "-" +dataList.get(position).getId());
        Glide.with(ctx).load(Uri.parse(dataList.get(position).getAvatar())).into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtEmailId;
        ImageView imgAvatar;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtEmailId = itemView.findViewById(R.id.txt_email_id);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
