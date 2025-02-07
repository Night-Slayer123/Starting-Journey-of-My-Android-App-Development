package com.driven.notetakingapp.ViewModel_Factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.driven.notetakingapp.RoomModel.Repository.NoteRepository
import com.driven.notetakingapp.ViewModel.Note_ViewModel

class Note_ViewModel_Factory (val app:Application,val repository: NoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Note_ViewModel::class.java)){
            return Note_ViewModel(app,repository) as T
        }
       throw IllegalArgumentException("Unknown ViewModel")
    }
}