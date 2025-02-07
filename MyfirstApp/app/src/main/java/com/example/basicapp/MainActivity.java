package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNow(View view){
        Toast.makeText(this, "Sending data from App", Toast.LENGTH_SHORT).show();
    }

    public void recieveNow(View view){
        Toast.makeText(this, "Recieving data from App", Toast.LENGTH_SHORT).show();
    }
    public void deleteNow(View view){
        Toast.makeText(this, "Deleting data from App", Toast.LENGTH_SHORT).show();
    }

}