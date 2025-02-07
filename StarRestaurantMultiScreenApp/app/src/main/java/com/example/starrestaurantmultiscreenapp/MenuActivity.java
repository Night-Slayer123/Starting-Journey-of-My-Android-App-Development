package com.example.starrestaurantmultiscreenapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String message=intent.getStringExtra(MainActivity.MSG);
    }

    public void tapped(View view){

        ImageView image=(ImageView) view;
        String tapped_location = image.getTag().toString();
        int tapped = Integer.parseInt(tapped_location);

        if(tapped==1){
            Toast.makeText(this, "Burger-Section", Toast.LENGTH_SHORT).show();
        } else if (tapped==2) {
            Toast.makeText(this, "Pizza-Section", Toast.LENGTH_SHORT).show();
        } else if (tapped==3) {
            Toast.makeText(this, "Chicken Bucket-Section", Toast.LENGTH_SHORT).show();
        } else if (tapped==4) {
            Toast.makeText(this, "Pasta-Section", Toast.LENGTH_SHORT).show();
        }
    }
}