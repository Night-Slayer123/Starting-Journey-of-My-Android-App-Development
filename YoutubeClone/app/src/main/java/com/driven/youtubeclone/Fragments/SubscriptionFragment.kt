package com.driven.youtubeclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import androidx.viewpager2.widget.ViewPager2
import com.driven.youtubeclone.Adapter.Search_Adapter
import com.driven.youtubeclone.Adapter.ViewPagerAdapter
import com.driven.youtubeclone.OnSearch_VideoListener.OnSearchClick_VideoAction
import com.driven.youtubeclone.R
import com.driven.youtubeclone.RequestApi.SearchRetrofit_Object
import com.driven.youtubeclone.SearchModel.ModelSearch
import com.driven.youtubeclone.Subscription_Model.Subs_model
import com.driven.youtubeclone.databinding.FragmentSubscriptionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubscriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubscriptionFragment : Fragment() {

    private val Api_Key = "AIzaSyDGe-JVkPJVaIJHnEwyphYleMhrcNK5Bv4"
    lateinit var binding: FragmentSubscriptionBinding

    private val part = "snippet"
    private val type = "video"
    private val results = 50

    private lateinit var name:String
    private lateinit var image:String
    lateinit var list: MutableList<Subs_model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubscriptionBinding.inflate(layoutInflater,container,false)

        retriving_Data()
        setData()
        if(binding.subsVideos.isNotEmpty()){
            binding.subsVideos.visibility = View.VISIBLE
            loadData()
        }
        else{
            binding.subsVideos.visibility = View.GONE
        }

        return binding.root
    }

    private fun loadData(){

        SearchRetrofit_Object.api.searchVideos(part,type,results,name,Api_Key).enqueue(object : Callback<ModelSearch?> {
            override fun onResponse(p0: Call<ModelSearch?>, response: Response<ModelSearch?>) {
                if(isAdded){
                    if (response.isSuccessful){
                        binding.textView23.visibility = View.GONE
                        val list = response.body()?.items?: emptyList()
                        binding.subsVideos.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = Search_Adapter(requireContext(),list,OnSearchClick_VideoAction(requireContext()))
                        binding.subsVideos.adapter = adapter
                    }
                    else {
                        Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(p0: Call<ModelSearch?>, p1: Throwable) {
                Toast.makeText(requireContext(),"Failed Due To:${p1.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun retriving_Data(){

       name = arguments?.getString("Subs_ChannelName")?:""
        image = arguments?.getString("Subs_ChannelImage")?:""
    }

    private fun setData(){
        list = mutableListOf(Subs_model(name,image))
        binding.viewPager2.adapter = ViewPagerAdapter(requireContext(),list)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}