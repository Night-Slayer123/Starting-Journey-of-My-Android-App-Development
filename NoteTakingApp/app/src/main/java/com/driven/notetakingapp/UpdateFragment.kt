package com.driven.notetakingapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.driven.notetakingapp.Adapter.NoteAdapter
import com.driven.notetakingapp.RoomModel.Model.Note
import com.driven.notetakingapp.ViewModel.Note_ViewModel
import com.driven.notetakingapp.databinding.FragmentUpdateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment(R.layout.fragment_update) {

    private var _binding:FragmentUpdateBinding?=null
    private val binding get() = _binding

    private lateinit var notesViewModel:Note_ViewModel
    private lateinit var noteAdapter:NoteAdapter

    private lateinit var currentNote: Note
    private val args:UpdateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewmodel
        currentNote = args?.note!!

        binding?.updatenoteTitle?.setText(currentNote.noteTitle)
        binding?.updatenote?.setText(currentNote.noteBody)

        binding?.updateButton?.setOnClickListener(){
            val title = binding!!.updatenoteTitle.toString().trim()
            val body = binding!!.updatenote.toString().trim()

            if(title.isNotEmpty()){
                val note = Note(currentNote.id,currentNote.noteTitle,currentNote.noteBody)
                notesViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            } else{
                Toast.makeText(requireContext(),"Please fill the fields",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Note")
            .setNegativeButton("No",){_,_->

            }
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes"){_,_->
                notesViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateFragment_to_homeFragment)
            }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete->{ deleteNote()}
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}