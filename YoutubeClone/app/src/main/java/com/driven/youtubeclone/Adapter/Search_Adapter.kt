package com.driven.youtubeclone.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driven.youtubeclone.OnSearch_VideoListener.OnSearchClick_VideoAction
import com.driven.youtubeclone.R
import com.driven.youtubeclone.SearchModel.Item
import com.squareup.picasso.Picasso

class Search_Adapter(private val context: Context,private val list:List<Item>,private val listener:OnSearchClick_VideoAction) :RecyclerView.Adapter<Search_Adapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Search_Adapter.viewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.home_layout,parent,false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Search_Adapter.viewHolder, position: Int) {

        val result = list[position]

        holder.title.text = result.snippet.title
        holder.channel_name.text = result.snippet.channelTitle
        holder.date.text=result.snippet.publishedAt.substring(0,10)
        holder.duration.visibility = View.GONE
        Picasso.get().load( result.snippet.thumbnails.high.url).into(holder.image)

        holder.itemView.setOnClickListener(){
            listener.clicklistener(result)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var title: TextView
        lateinit var channel_name: TextView
        lateinit var image: ImageView
        lateinit var date: TextView
        lateinit var duration: TextView

        init {
            title = itemView.findViewById(R.id.textView7)
            channel_name = itemView.findViewById(R.id.textView8)
            date = itemView.findViewById(R.id.textView9)
            image = itemView.findViewById(R.id.imageView4)
            duration = itemView.findViewById(R.id.textView17)
        }
    }
}