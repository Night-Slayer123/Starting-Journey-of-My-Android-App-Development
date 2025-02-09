package com.driven.addupdateinrecyclerview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<DetailModel> list;
    Adapter adapt;
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rc = findViewById(R.id.recycler);
        rc.setLayoutManager(new GridLayoutManager(this,2));

        list = new ArrayList<>();

        FloatingActionButton ftb = findViewById(R.id.floatingActionButton);
        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_box);

                EditText edt = dialog.findViewById(R.id.txtname);
                EditText edt2 = dialog.findViewById(R.id.txtcontact);
                Button btnAction = dialog.findViewById(R.id.button);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = null,number = null;


                        if(!((edt.equals(null) && edt2.equals(null))|| (edt.length()<5 && edt2.length()<11))){
                            name = edt.getText().toString();
                            number = edt2.getText().toString();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please Fill your text/At-least contain 5 characters for name or 11 for contact", Toast.LENGTH_SHORT).show();
                        }

                        list.add(new DetailModel(name,number));
                        adapt.notifyItemInserted(list.size()-1);
                        rc.scrollToPosition(list.size()-1);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        list.add(new DetailModel("Moiz","03471554461"));
        list.add(new DetailModel("Huzaifa","03326063007"));
        list.add(new DetailModel("Tayyaab Bohat Pak","03315530616"));
        list.add(new DetailModel("Shah g","03160501251"));
        list.add(new DetailModel("Army waly bhai","03005547727"));
        list.add(new DetailModel("Zeemi Bhai","03435041018"));

        adapt = new Adapter(this,list);
        rc.setAdapter(adapt);
    }
}