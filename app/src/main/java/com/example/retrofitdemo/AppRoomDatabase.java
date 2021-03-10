package com.example.retrofitdemo;

import android.content.Context;

import com.example.retrofitdemo.model.Data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Data.class, exportSchema = false, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static final String DB_NAME = "data_room";
    private static AppRoomDatabase roomDatabase;

    public static synchronized AppRoomDatabase getInstance(Context ctx){
        if(roomDatabase == null){
            roomDatabase = Room.databaseBuilder(ctx,AppRoomDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return roomDatabase;
    }

    public abstract DaoInterface daoInterface();
}
