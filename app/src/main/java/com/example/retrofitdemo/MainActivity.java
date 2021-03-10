package com.example.retrofitdemo;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofitdemo.model.Data;
import com.example.retrofitdemo.model.Users;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recUsers;
    List<Data> allData;
    UserAdapter adapter;
    ProgressBar progressBar;
    int page=1, limit=2;
    AppRoomDatabase appRoomDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recUsers = findViewById(R.id.rec_users);
        recUsers.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        allData = new ArrayList<>();
        appRoomDatabase = AppRoomDatabase.getInstance(this);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        if(appRoomDatabase.daoInterface().fetchUserData().toString().equals("[]")){
            Toast.makeText(MainActivity.this, "From API", Toast.LENGTH_SHORT).show();
            Call<Users> call = ApiClient.getInstance().getApi().getUserInfo(page, limit);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.isSuccessful()){
                        allData.addAll(response.body().getData());
                        setUserAdapter(allData);
                        for(int i=0; i<allData.size(); i++){
                            Data data = allData.get(i);
                            Log.d("room",data.toString());
                            appRoomDatabase.daoInterface().insertUserData(data);
                        }
                        progressBar.setVisibility(View.INVISIBLE);

                        recUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                if(page<limit){
                                    progressBar.setVisibility(View.VISIBLE);
                                    page++;
                                    Toast.makeText(MainActivity.this, "performPagination", Toast.LENGTH_SHORT).show();
                                    performPagination();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
        else {
            Toast.makeText(MainActivity.this, "From Room db", Toast.LENGTH_SHORT).show();
            allData.addAll(appRoomDatabase.daoInterface().fetchUserData());
            setUserAdapter(allData);
            progressBar.setVisibility(View.INVISIBLE);
        }

        Log.d("room" ,appRoomDatabase.daoInterface().fetchUserData().toString());
            }

    private void setUserAdapter(List<Data> dataList) {
        adapter = new UserAdapter(dataList, MainActivity.this);
        recUsers.setAdapter(adapter);
    }

    private void performPagination() {
        Call<Users> call = ApiClient.getInstance().getApi().getUserInfo(page, limit);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    List<Data> anotherData = response.body().getData();
                    adapter.addData(anotherData);
                    for(int i=0; i<anotherData.size(); i++){
                        Data data = anotherData.get(i);
                        appRoomDatabase.daoInterface().insertUserData(data);
                    }
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
        progressBar.setVisibility(View.INVISIBLE);
    }
}