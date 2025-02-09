package com.driven.youtubeclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.driven.youtubeclone.Adapter.Search_Adapter
import com.driven.youtubeclone.OnSearch_VideoListener.OnSearchClick_VideoAction
import com.driven.youtubeclone.R
import com.driven.youtubeclone.RequestApi.SearchRetrofit_Object
import com.driven.youtubeclone.SearchModel.ModelSearch
import com.driven.youtubeclone.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private val Api_Key = "AIzaSyDGe-JVkPJVaIJHnEwyphYleMhrcNK5Bv4"
    lateinit var binding: FragmentSearchBinding

    private val part = "snippet"
    private val type = "video"
    private val results = 50
    private lateinit var search_query:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)

        search_query = arguments?.getString("Query_Searched") ?: ""
       

        SearchData()

        return binding.root
    }

    fun SearchData(){

        SearchRetrofit_Object.api.searchVideos(part,type,results,search_query,Api_Key).enqueue(object : Callback<ModelSearch?> {
            override fun onResponse(p0: Call<ModelSearch?>, response: Response<ModelSearch?>) {

                if(isAdded){
                    if (response.isSuccessful){
                        val list = response.body()?.items?: emptyList()
                        binding.SearchedVideos.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = Search_Adapter(requireContext(),list,OnSearchClick_VideoAction(requireContext())
                        )
                        binding.progressBar4.visibility=View.GONE
                        binding.SearchedVideos.adapter = adapter
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
}