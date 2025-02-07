package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText text1,text2,text3;
    TextView txt;
    AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.weight);
        Animation anim_txt = AnimationUtils.loadAnimation(this,R.anim.slide_x);
        text1.startAnimation(anim_txt);

        text2=findViewById(R.id.height);
        Animation anim_txt2 = AnimationUtils.loadAnimation(this,R.anim.slide_x);
        text2.startAnimation(anim_txt2);

        text3=findViewById(R.id.height_in);
        Animation anim_txt3 = AnimationUtils.loadAnimation(this,R.anim.slide_x);
        text3.startAnimation(anim_txt3);

        button = findViewById(R.id.calculate);
        Animation anim_button = AnimationUtils.loadAnimation(this,R.anim.slide_y);
        button.startAnimation(anim_button);


        ImageView img = findViewById(R.id.imageView8);
        Animation anim_img = AnimationUtils.loadAnimation(this,R.anim.slide_y);
        img.startAnimation(anim_img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double weight=Integer.parseInt(text1.getText().toString());
                double height=Integer.parseInt(text2.getText().toString());
                double height_in=Integer.parseInt(text3.getText().toString());

                double result = height*12+height_in;

                double result_cm= result * 2.53;

                double result_meter = result_cm/100;

                double bmi_result= weight/(Math.pow(result_meter,2));

                if(bmi_result > 25){
                    txt.setText("! You are OverWeight !");
                    txt.setVisibility(View.VISIBLE);
                } else if (bmi_result<18) {
                    txt.setText("! You are UnderWeight !");
                    txt.setVisibility(View.VISIBLE);
                }
                else {
                    txt.setText("! You are Healthy !");
                    txt.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}