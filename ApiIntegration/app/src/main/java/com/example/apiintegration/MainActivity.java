package com.example.apiintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String url = "https://jsonplaceholder.typicode.com";
    List<UserModel> data;

    RecyclerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.recycle);
        view.setLayoutManager(new LinearLayoutManager(this));


        RetrofitBuilder.getInstance().api.getUser().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                data=response.body();
                view.setAdapter(new UserAdapter(MainActivity.this,data));

            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Log.d("api","Failed due to"+t.getLocalizedMessage());
            }
        });
    }
}