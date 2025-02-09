package com.driven.youtubeclone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.driven.youtubeclone.databinding.ActivityAccountsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class Accounts : AppCompatActivity() {

    lateinit var binding:ActivityAccountsBinding
    private lateinit var user_mail:String
    private lateinit var user_name:String

    lateinit var gso:GoogleSignInOptions
    lateinit var gsc:GoogleSignInClient

    lateinit var GUSER_NAME:String
    lateinit var GUSER_EMAILADDRESS:String

    private val RC_SIGN_IN = 1000

    private var LOGGED_IN = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAccountsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso)

        ClickEvents()
    }

    private fun ClickEvents(){

        binding.appCompatButton.setOnClickListener(){
            setLogInState()
        }
        binding.AppCompactButton2.setOnClickListener(){
            SigningIn()
        }
    }

    private fun SigningIn(){

        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun updateUI(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun handleSignInResult(account: GoogleSignInAccount?) {
        if (account != null) {
            GUSER_NAME = account.displayName ?: ""
            GUSER_EMAILADDRESS = account.email ?: ""
            Googledatapassing()
            updateUI()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                handleSignInResult(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun setLogInState(){
        if(binding.editTextTextEmailAddress.text!=null && binding.editTextTextPassword.text!=null ||
            binding.editTextTextEmailAddress.text.length>5 && binding.editTextTextPassword.text.length>5){
            user_name = binding.editTextTextEmailAddress.text.toString()
            val sharedPreferences = getSharedPreferences("login", MODE_PRIVATE).edit()
            sharedPreferences.putBoolean("Login_Flag",LOGGED_IN)
            sharedPreferences.apply()
            dataPassing()
            updateUI()
        }
        else{
            Toast.makeText(this@Accounts,"Please fill the fields/ Email or Password character Length must be >5 or not be null",Toast.LENGTH_SHORT).show()
        }

    }

    private fun Googledatapassing() {
        if (::GUSER_NAME.isInitialized && ::GUSER_EMAILADDRESS.isInitialized) {
            val bundle = Bundle().apply {
                putString("Google_UserName", GUSER_NAME)
                putString("Google_Email", GUSER_EMAILADDRESS)
            }
            val intent = Intent(this, Profile::class.java).apply {
                putExtras(bundle)
            }
        } else {
            Toast.makeText(this, "User information is not available. Please sign in first.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dataPassing(){
        val bundle = Bundle().apply {
            putString("UserName", user_name)
        }
        val intent = Intent(this, Profile::class.java).apply {
            putExtras(bundle)
        }
    }

}