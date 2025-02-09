package com.driven.youtubeclone.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driven.youtubeclone.R
import com.driven.youtubeclone.Subscription_Model.Subs_model
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ViewPagerAdapter(private val context: Context,private val list: MutableList<Subs_model>) :RecyclerView.Adapter<ViewPagerAdapter.viewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.viewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.subscription_layout,parent,false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.viewHolder, position: Int) {

        val item = list[position]
        holder.channelTitle.text = item.name
        Picasso.get().load(Uri.parse(item.image)).into(holder.channel_img)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        lateinit var channelTitle:TextView
        lateinit var channel_img:CircleImageView

        init {
            channelTitle = itemView.findViewById(R.id.textView19)
            channel_img = itemView.findViewById(R.id.circleImageView3)
        }
    }
}