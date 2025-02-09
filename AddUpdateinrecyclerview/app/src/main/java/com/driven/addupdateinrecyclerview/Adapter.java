package com.driven.addupdateinrecyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.userHolder> {
    Context context ;
    ArrayList<DetailModel> list;

    int last_position = -1;

    public Adapter(Context context, ArrayList<DetailModel>list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public Adapter.userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contacts,parent,false);
        userHolder userHolder = new userHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.userHolder holder, int position) {

        holder.name.setText(list.get(position).name);
        holder.number.setText(list.get(position).contact);
        holder.img.setImageResource(R.drawable.person);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_box);

                EditText editText,editText2;
                editText = dialog.findViewById(R.id.txtname);
                editText2 = dialog.findViewById(R.id.txtcontact);
                TextView txt = dialog.findViewById(R.id.textView4);

                txt.setText("Update Contact");
                Button btn = dialog.findViewById(R.id.button);
                btn.setText("Update");

                editText.setText(list.get(position).name);
                editText2.setText(list.get(position).contact);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name=null,number=null;

                        if(!((editText.equals(null) && editText2.equals(null))|| (editText.length()<5 && editText2.length()<11))){
                            name = editText.getText().toString();
                            number = editText2.getText().toString();
                        }
                        else {
                            Toast.makeText(context, "Please Fill your text/At-least contain 5 characters for name or 11 for contact", Toast.LENGTH_SHORT).show();
                        }

                        list.set(position,new DetailModel(name,number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are You Sure Want To Delete This ?")
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                list.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;
            }
        });

        animation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class userHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        TextView number;
        LinearLayout layout;
        public userHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView2);
            number = itemView.findViewById(R.id.textView3);
            img = itemView.findViewById(R.id.imageView);
            layout = itemView.findViewById(R.id.linear_layout);
        }
    }

    private void animation(View item,int position){

        if(position>last_position){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.recycler_anim);
            item.startAnimation(animation);
            last_position=position;
        }
    }
}
