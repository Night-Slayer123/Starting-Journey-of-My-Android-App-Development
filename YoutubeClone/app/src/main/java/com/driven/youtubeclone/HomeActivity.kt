package com.driven.youtubeclone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.driven.youtubeclone.Fragments.AddFragment
import com.driven.youtubeclone.Fragments.HomeFragment
import com.driven.youtubeclone.Fragments.ProfileFragment
import com.driven.youtubeclone.Fragments.SearchFragment
import com.driven.youtubeclone.Fragments.ShortsFragment
import com.driven.youtubeclone.Fragments.SubscriptionFragment
import com.driven.youtubeclone.databinding.ActivityHomeBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import me.ibrahimsn.lib.OnItemSelectedListener

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var user_query:String

    private var LogInState = false

    @OptIn(ExperimentalBadgeUtils::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val badgeDrawable = BadgeDrawable.create(this)
        badgeDrawable.number = 2
        badgeDrawable.isVisible = true

        BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.imageButton,null)

        binding.linearLayout2.visibility = View.VISIBLE

        if(savedInstanceState==null){
            loadFragment(HomeFragment())
        }
        clickHandling()

        binding.bottomBar.setOnItemSelectedListener { pos ->
            when (pos) {
                0 -> {
                    binding.linearLayout2.visibility = View.VISIBLE
                    loadFragment(HomeFragment())
                }
                1 -> {
                    loadFragment(ShortsFragment())
                    binding.linearLayout2.visibility = View.GONE
                }
                2-> {
                    loadFragment(AddFragment())
                    binding.linearLayout2.visibility = View.GONE
                }
                3-> {
                    binding.linearLayout2.visibility = View.VISIBLE
                    loadFragment(SubscriptionFragment())
                }
                4->{
                    loadFragment(ProfileFragment())
                    binding.linearLayout2.visibility = View.GONE
                }
            }
            true // Returning true consumes the event
        }
    }

    fun clickHandling(){
        binding.searchButton.setOnClickListener(){

            binding.linearLayout2.visibility= View.INVISIBLE
            binding.searchLinearlayout.visibility=View.VISIBLE

            binding.searchBar.clearFocus()

        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                user_query = query.toString()

                val searchFragment = SearchFragment()
                val bundle = Bundle()
                bundle.putString("Query_Searched", user_query)
                searchFragment.arguments = bundle

                loadFragment(searchFragment)
                binding.searchBar.clearFocus()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                user_query = newText.toString()
                return true
            }
        })
    }

    fun loadFragment(fragment:Fragment){

        val manager:FragmentManager = supportFragmentManager
        val transaction:FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

    private fun Login(){

        var SharedPreferences = getSharedPreferences("login", MODE_PRIVATE).edit()
        SharedPreferences.putBoolean("Login_Flag",LogInState)
        SharedPreferences.apply()
    }


    override fun onBackPressed() {
        if (binding.searchLinearlayout.visibility == View.VISIBLE) {
            binding.linearLayout2.visibility = View.VISIBLE
            binding.searchLinearlayout.visibility = View.GONE
            loadFragment(HomeFragment())
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack() // Go back to the previous fragment
        } else {super.onBackPressed() // Default behavior if no fragments in back stack
        }
    }
}