package com.driven.youtubeclone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.driven.youtubeclone.Fragments.SubscriptionFragment
import com.driven.youtubeclone.On_SubscribeListener.On_SubscribeInterface
import com.driven.youtubeclone.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    lateinit var title:String
    lateinit var channelname:String
    lateinit var view:String
    lateinit var description:String
    lateinit var image :String
    lateinit var caption:String
    lateinit var date:String
    lateinit var comments:String

    lateinit var binding: ActivityDetailBinding

    lateinit var listener:On_SubscribeInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retrieveData()
        dataSetting()
        ClickListeners()
    }

    fun retrieveData(){
        if(intent.getBooleanExtra("Home",false)){

            intent.apply {
                title = getStringExtra("Videotitle").toString()
                channelname = getStringExtra("Videochannel").toString()
                view = getStringExtra("Videoviews").toString()
                image = getStringExtra("Videoimage").toString()
                description = getStringExtra("Videodescription").toString()
                caption = getStringExtra("Videocaption").toString()
                date = getStringExtra("VideoDate").toString()
                comments = getStringExtra("Videocomment").toString()
            }
        }
        else if (intent.getBooleanExtra("Search",false) && !intent.hasExtra("Home")){

            intent.apply {
                title = getStringExtra("SVideotitle").toString()
                channelname = getStringExtra("SVideochannel").toString()
                view = getStringExtra("SVideoviews").toString()
                image = getStringExtra("SVideoimage").toString()
                description = getStringExtra("SVideodescription").toString()
                caption = getStringExtra("SVideocaption").toString()
                date = getStringExtra("SVideoDate").toString()
                comments = getStringExtra("SVideocomment").toString()
            }
        }
    }

    fun dataSetting(){

        val releasedate = date.substring(0,10)
        Picasso.get().load(image).into(binding.imageView5)
        binding.textView10.text = title
        binding.textView11.text = channelname
        binding.textView13.text=comments
        binding.textView14.text = view
        binding.textView15.text=releasedate
        binding.textView21.text = description
    }

    fun ClickListeners(){

        binding.button2.setOnClickListener(){
            if(!binding.button2.isSelected){
                binding.button2.isSelected = true
                listener = object : On_SubscribeInterface{
                    override fun On_Subscribe(channel_Name: String, channel_image: String) {
                        val bundle = Bundle().apply {
                            putString("Subs_ChannelName",channel_Name)
                            putString("Subs_ChannelImage",channel_image)
                        }
                        val intent = Intent(this@DetailActivity, SubscriptionFragment::class.java).apply {
                            putExtras(bundle)
                        }
                    }

                }
                binding.button2.text = "UnSubscribe"
                binding.button2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_notifications_active_24,0,0,0)
                binding.button2.setTextColor(getColor(R.color.red))
                binding.button2.setBackgroundColor(resources.getColor(R.color.white))
            }

            else{
                binding.button2.isSelected = false
                binding.button2.text = "Subscirbe"
                binding.button2.setTextColor(getColor(R.color.white))
                binding.button2.setBackgroundColor(resources.getColor(R.color.red))
                binding.button2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
            }
        }

        binding.textView16.setOnClickListener(){
            val animation: Animation = AnimationUtils.loadAnimation(this,R.anim.slide_in)
            binding.descriptionLayout.startAnimation(animation)
            binding.descriptionLayout.visibility = View.VISIBLE
            binding.cardView2.visibility = View.GONE
        }

    }

    override fun onBackPressed() {

        if(binding.descriptionLayout.visibility != View.VISIBLE){
            super.onBackPressed()
        }
        else{
            val animation = AnimationUtils.loadAnimation(this,R.anim.slide_out)
            binding.descriptionLayout.startAnimation(animation)
            binding.descriptionLayout.visibility = View.GONE
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    binding.cardView2.visibility = View.VISIBLE
                }
            }, 1000)

        }
    }
}