package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;

    String Winner=null;

    boolean GameStart=true;
    int[]gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][]WinningSections={{1,2,3},{4,5,6},{7,8,9},
                            {1,4,7},{2,5,8},{3,6,9},
                            {1,5,9},{3,5,7}};
    public void PlayerTapped(View view){
        ImageView img = (ImageView) view;
        String tappLocation= img.getTag().toString();
        int tappedLocation = Integer.parseInt(tappLocation);
        if(!GameStart){
            reset(view);
        }
        if(gameState[tappedLocation]==2 && GameStart){
            gameState[tappedLocation]=activePlayer;

            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn-Tap To Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn-Tap To Play");
            }

            img.animate().translationYBy(1000f).setDuration(150);
        }

        for(int[]W_Position:WinningSections){
            if(gameState[W_Position[0]]==gameState[W_Position[1]] &&
                    gameState[W_Position[1]]==gameState[W_Position[2]] &&
                    gameState[W_Position[0]]!=gameState[W_Position[2]]){
                    GameStart=false;
                if(gameState[W_Position[0]]==0){
                    Winner="X has Won";
                }
                else{
                    Winner="O has Won";
                }

                TextView status = findViewById(R.id.status);
                status.setText(Winner);
            }
        }

    }

    public void reset(View view){
        for(int i=1;i<=2;i++){
            GameStart=true;
            activePlayer=0;
        }

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}