package com.driven.youtubeclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.driven.youtubeclone.Adapter.RecyclerView_Adapter
import com.driven.youtubeclone.OnCLickAction.Click_Action
import com.driven.youtubeclone.Model.Item
import com.driven.youtubeclone.Model.Youtube_Model
import com.driven.youtubeclone.Repository.Repository
import com.driven.youtubeclone.RequestApi.RetrofitObject
import com.driven.youtubeclone.ViewModel.YoutubeViewModel
import com.driven.youtubeclone.ViewModelFactory.YoutubeViewModelFactory
import com.driven.youtubeclone.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding

    // Url:https://www.googleapis.com/youtube/v3/search

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        val repository = Repository()
        val factory = YoutubeViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,factory)[YoutubeViewModel::class.java]
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.data.observe(viewLifecycleOwner, Observer { videolist->
            binding.progressBar2.visibility = View.GONE
            binding.RecyclerView.adapter = RecyclerView_Adapter(requireContext(),videolist,Click_Action(requireContext()))
        })

        return binding.root
    }
}