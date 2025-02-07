package com.driven.notetakingapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.driven.notetakingapp.RoomModel.DataBase.Note_DataBase
import com.driven.notetakingapp.RoomModel.Repository.NoteRepository
import com.driven.notetakingapp.ViewModel.Note_ViewModel
import com.driven.notetakingapp.ViewModel_Factory.Note_ViewModel_Factory
import com.driven.notetakingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var noteViewmodel: Note_ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val repository = NoteRepository(Note_DataBase(this))
        val factory = Note_ViewModel_Factory(application, repository)

        noteViewmodel = ViewModelProvider(this, factory).get(Note_ViewModel::class.java)

    }
}