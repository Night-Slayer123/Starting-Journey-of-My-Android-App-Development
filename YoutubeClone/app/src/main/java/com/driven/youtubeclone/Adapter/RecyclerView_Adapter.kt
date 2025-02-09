package com.driven.youtubeclone.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driven.youtubeclone.Model.Item
import com.driven.youtubeclone.OnClick_VideoListener.VideoClickListener
import com.driven.youtubeclone.R
import com.squareup.picasso.Picasso


class RecyclerView_Adapter(val context:Context,private val list:List<Item>,val listener:VideoClickListener): RecyclerView.Adapter<RecyclerView_Adapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView_Adapter.viewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.home_layout,parent,false)

        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView_Adapter.viewHolder, position: Int) {

        val result = list[position]

        holder.title.text = result.snippet.title
        holder.channel_name.text = result.snippet.channelTitle
        holder.date.text=result.snippet.publishedAt
        val duration = result.contentDetails.duration
        holder.duration.text = duration.substring(2)

        Picasso.get().load( result.snippet.thumbnails.standard.url).into(holder.image)

        holder.itemView.setOnClickListener(){
            listener?.VideoListener(result)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        lateinit var title: TextView
        lateinit var channel_name: TextView
        lateinit var image:ImageView
        lateinit var date: TextView
        lateinit var duration:TextView

        init {
            title = itemView.findViewById(R.id.textView7)
            channel_name = itemView.findViewById(R.id.textView8)
            date = itemView.findViewById(R.id.textView9)
            image = itemView.findViewById(R.id.imageView4)
            duration = itemView.findViewById(R.id.textView17)
        }
    }
}