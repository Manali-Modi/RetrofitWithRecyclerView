package com.example.retrofitdemo.model;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userData")
public class Data {

    //annotation @SerializedName is necessary in case of variable name is diff from the json output
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "user_id")
    private String id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name") private String firstName;

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name") private String lastName;

    @ColumnInfo(name = "avatar")
    private String avatar;

    public Data(String id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @BindingAdapter("avatar")
    public static void loadAvatar(ImageView view,String uri){
        Glide.with(view.getContext()).load(Uri.parse(uri)).into(view);
    }

}
