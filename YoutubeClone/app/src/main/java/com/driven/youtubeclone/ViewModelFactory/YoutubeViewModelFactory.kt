package com.driven.youtubeclone.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driven.youtubeclone.Repository.Repository
import com.driven.youtubeclone.ViewModel.YoutubeViewModel

class YoutubeViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(YoutubeViewModel::class.java)){
            return YoutubeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}