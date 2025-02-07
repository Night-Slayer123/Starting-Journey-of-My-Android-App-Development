package com.example.starrestaurantmultiscreenapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MSG ="com.example.Star Restaurant.multiscreenapp.msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView animatedText=findViewById(R.id.animatedTextView);
        ImageView animatedImage=findViewById(R.id.imageView);
        Button button = findViewById(R.id.button2);

        Animation fade_animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fade_animation.reset();
        Animation button_animation=AnimationUtils.loadAnimation(this,R.anim.button_fade);
        button_animation.reset();
        animatedText.startAnimation(fade_animation);
        button.startAnimation(button_animation);
        animatedImage.startAnimation(fade_animation);

    }

    public void NextScreen(View view){

        Intent intent = new Intent(this,MenuActivity.class);
        Toast.makeText(this, "Welcome To Star-Restaurant", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}