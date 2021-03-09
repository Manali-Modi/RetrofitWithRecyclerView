package com.example.retrofitdemo;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofitdemo.Model.Data;
import com.example.retrofitdemo.Model.Users;

import java.util.ArrayList;
import java.util.List;

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
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recUsers = findViewById(R.id.rec_users);
        recUsers.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        allData = new ArrayList<>();
        appDatabase = new AppDatabase(this);

        Cursor cursor = appDatabase.getDataFromDB();
        if (cursor != null) {
            int count = cursor.getCount();
            if (count == 0) {
                Toast.makeText(MainActivity.this, "From API", Toast.LENGTH_SHORT).show();
                Call<Users> call = ApiClient.getInstance().getApi().getUserInfo(2);

                call.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if (response.isSuccessful()) {
                            allData.addAll(response.body().getData());
                            setUserAdapter(allData);
                            for (int i = 0; i < allData.size(); i++) {
                                appDatabase.insertData(allData.get(i).getId(), allData.get(i).getEmail(),
                                        allData.get(i).getFirstName(), allData.get(i).getLastName(), allData.get(i).getAvatar());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Log.d("error", t.getMessage());
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "From database", Toast.LENGTH_SHORT).show();
                while (cursor.moveToNext()) {
                    String userId = cursor.getString(cursor.getColumnIndex("user_id"));
                    String email = cursor.getString(cursor.getColumnIndex("email"));
                    String firstName = cursor.getString(cursor.getColumnIndex("first_name"));
                    String lastName = cursor.getString(cursor.getColumnIndex("last_name"));
                    String avatar = cursor.getString(cursor.getColumnIndex("avatar"));

                    Data data = new Data(userId, email, firstName, lastName, avatar);
                    allData.add(data);
                    setUserAdapter(allData);
                }
            }
        }

    }

    private void setUserAdapter(List<Data> dataList) {
        adapter = new UserAdapter(dataList, MainActivity.this);
        recUsers.setAdapter(adapter);
    }
}