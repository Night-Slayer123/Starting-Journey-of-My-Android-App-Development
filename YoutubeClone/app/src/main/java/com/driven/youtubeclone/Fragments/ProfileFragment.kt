package com.driven.youtubeclone.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.driven.youtubeclone.R
import com.driven.youtubeclone.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    private val GALLERY_REQ_CODE = 1001
    private val BANNER_GALLERY_REQ_CODE = 1002

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)

        RetriveAndSettingData()
        Animation()
        clickEvents()

        return binding.root
    }

    private fun Animation(){
        val slide = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in)
        binding.linearLayout3.startAnimation(slide)

        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.slide_inx)
        binding.editTextText2.startAnimation(animation)
        binding.editTextText3.startAnimation(animation)
    }

    private fun clickEvents(){

        binding.editTextText4.setOnClickListener(){
            val name = binding.editTextText4.text
            binding.editTextText4.text = name
            binding.editTextText4.clearFocus()
        }

        binding.editTextText2.setOnClickListener(){
            val handler = binding.editTextText2.text
            binding.editTextText2.text = handler
            binding.editTextText2.clearFocus()
        }

        binding.editTextText3.setOnClickListener(){
            val description = binding.editTextText3.text
            binding.editTextText3.text = description
            binding.editTextText3.clearFocus()
        }

        binding.imageButton4.setOnClickListener(){
            val intent = Intent(Intent.ACTION_PICK)
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,GALLERY_REQ_CODE)
        }
        binding.imageButton3.setOnClickListener(){
                val intent = Intent(Intent.ACTION_PICK)
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,BANNER_GALLERY_REQ_CODE)
        }
    }

    private fun RetriveAndSettingData(){
        
        val username= arguments?.getString("UserName")
        binding.textView22.text= username
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                binding.circleProfile.setImageURI(data?.data)
            }
            else if(requestCode==BANNER_GALLERY_REQ_CODE){
                binding.imageView.setImageURI(data?.data)
            }
        }
    }
}