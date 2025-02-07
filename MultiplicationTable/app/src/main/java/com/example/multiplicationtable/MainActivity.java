package com.example.multiplicationtable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar bar = findViewById(R.id.seekBar);
        bar.setMax(20);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                populate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void populate(int progress){

        list = findViewById(R.id.listView);

        ArrayList<String> multiplication = new ArrayList<>();
        for(int i=1;i<=20;i++){

            multiplication.add(progress+" X "+i+" = "+progress*i);
        }

        ArrayAdapter<String>display= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,multiplication);
        list.setAdapter(display);
    }
}