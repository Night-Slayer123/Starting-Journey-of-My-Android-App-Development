package com.driven.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.driven.weatherapp.RequestApi_Object.RetrofitObject
import com.driven.weatherapp.RequestModel.Weather_Model
import com.driven.weatherapp.databinding.ActivityWeatherCastingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherCasting : AppCompatActivity() {

    lateinit var binding: ActivityWeatherCastingBinding
    private val api_key = "64891bc20a024caba5a5dca86e9c2b18"
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_weather_casting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        GetData()
        DropMenu()

    }

    fun GetData(){

        RetrofitObject.api.Weather_Data("London",api_key).enqueue(object : Callback<Weather_Model?> {
            override fun onResponse(p0: Call<Weather_Model?>, response: Response<Weather_Model?>) {

                if(response.isSuccessful && response.body()!==null){
                    val temp = response.body()?.main?.temp
                    val temp_celcius = temp?.minus(273.15)

                    val pressure = response.body()?.main?.pressure
                    val windspeed = response.body()?.wind?.speed
                    val humidity = response.body()?.main?.humidity

                    runOnUiThread {

                        binding.textView6.text = "$temp_celcius °C"
                        binding.textView10.text="$pressure Pa"
                        binding.textView11.text="$windspeed m/s"
                        binding.textView12.text = "$humidity %"

                    }

                    position++

                    Log.d("WeatherTemp", "Temperature: $temp_celcius")
                    Log.d("WeatherPressure", "Pressure: $pressure")
                    Log.d("WeatherWind", "Windspeed: $windspeed")
                    Log.d("WeatherHumidity", "Humidity: $humidity")
                }

                else{
                    Toast.makeText(this@WeatherCasting,"Having Issue With Api, Failed Due To:${response.message()}",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(p0: Call<Weather_Model?>, p1: Throwable) {
                Toast.makeText(this@WeatherCasting,"Failed Due To:${p1.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun DropMenu(){
        val array = listOf("London","New York","Chicago")
        binding.imageView2.setOnClickListener(){
            val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,array)
        }

    }
}