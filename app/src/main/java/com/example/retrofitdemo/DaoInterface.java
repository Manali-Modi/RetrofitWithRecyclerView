package com.example.retrofitdemo;

import com.example.retrofitdemo.model.Data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoInterface {

    @Query("select * from userData")
    List<Data> fetchUserData();

    @Insert
    void insertUserData(Data data);

    @Update
    void updateUserData(Data data);

    @Delete
    void deleteUserData(Data data);
}
