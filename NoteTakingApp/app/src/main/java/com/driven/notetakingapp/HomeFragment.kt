package com.driven.notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.driven.notetakingapp.Adapter.NoteAdapter
import com.driven.notetakingapp.RoomModel.Model.Note
import com.driven.notetakingapp.ViewModel.Note_ViewModel
import com.driven.notetakingapp.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding

    private lateinit var viewModel: Note_ViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)



        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).noteViewmodel
        setUpRecyclerView()
        binding?.floatingActionButton?.setOnClickListener(){
            it.findNavController().navigate(R.id.action_homeFragment_to_new_NoteFragment)
        }
    }

    private fun setUpRecyclerView() {
       val noteadapter = NoteAdapter()
        binding?.recyclerVIew?.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteadapter

        }
        activity?.let {
            viewModel.getAllNotes().observe(viewLifecycleOwner, Observer { note->
                noteadapter.differ.submitList(note)
                updateUi(note)
            })
        }
    }

    private fun updateUi(note: List<Note>?) {
        if (note != null) {
            if(note.isNotEmpty()){
                binding?.cardView?.visibility?: View.GONE
                binding?.recyclerVIew?.visibility ?: View.VISIBLE
            }
            else{
                binding?.cardView?.visibility?:View.VISIBLE
                binding?.recyclerVIew?.visibility?:View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)

        val menuSearch = menu.findItem(R.id.home_search).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchNote(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            searchNote(newText)
        }
        return true
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query"
        viewModel.searchNote(searchQuery).observe(this, Observer { list->
            adapter.differ.submitList(list)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}