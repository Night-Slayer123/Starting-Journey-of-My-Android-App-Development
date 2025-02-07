package com.driven.notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.driven.notetakingapp.Adapter.NoteAdapter
import com.driven.notetakingapp.RoomModel.Model.Note
import com.driven.notetakingapp.ViewModel.Note_ViewModel
import com.driven.notetakingapp.databinding.FragmentHomeBinding
import com.driven.notetakingapp.databinding.FragmentNewNoteBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [New_NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class New_NoteFragment : Fragment() {

    private var _binding: FragmentNewNoteBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: Note_ViewModel
    private lateinit var adapter: NoteAdapter

    lateinit var mview:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(inflater,container,false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mview = view
    }
    private fun saveNote(view:View){
        val noteTitle = binding.noteTitle.text.toString().trim()
        val noteBody = binding.noteBody.text.toString().trim()
        if(noteTitle.isNotEmpty()){
            val note = Note(0,noteTitle,noteBody)
            viewModel.addNote(note)
            Toast.makeText(requireContext(),"Note Saved Successfully",Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_new_NoteFragment_to_homeFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please Enter Note Title",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.menu_new_note,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save->{ saveNote(mview)}
        }
        return super.onOptionsItemSelected(item)
    }

}