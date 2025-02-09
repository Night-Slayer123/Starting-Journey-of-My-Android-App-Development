package com.driven.youtubeclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.driven.youtubeclone.Adapter.Shorts_Adapter
import com.driven.youtubeclone.R
import com.driven.youtubeclone.databinding.FragmentShortsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShortsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShortsFragment : Fragment() {

    lateinit var binding: FragmentShortsBinding
    lateinit var list: List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShortsBinding.inflate(layoutInflater,container,false)

        list = listOf("https://www.youtube.com/shorts/1l7QG5IZtgY"
            ,"https://www.youtube.com/shorts/FETQLU0wpJ8",
            "https://www.youtube.com/shorts/AoJ1XBTN6BE",
            "https://www.youtube.com/shorts/H_st6W6csPo",
            "https://www.youtube.com/shorts/9EhrwW0iw8s",
            "https://www.youtube.com/shorts/e8Bu7TBSA4o",
            "https://www.youtube.com/shorts/mvvt4Kj8BnE",
            "https://www.youtube.com/shorts/d0FR9_xkNok")

        val adapter = Shorts_Adapter(requireContext(),list)
        binding.shortsViewPager.adapter=adapter
        binding.shortsViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        return binding.root
    }
}