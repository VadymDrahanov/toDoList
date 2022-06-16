package com.example.zaribatodolist.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.zaribatodolist.MainActivity

import com.example.zaribatodolist.data.repository.AuthRepository
import com.example.zaribatodolist.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel
    var firebaseUser: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //refactor it
        val factory = AuthViewModelFactory(AuthRepository(applicationContext))

        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        viewModel.getFirebaseUser()

        val observer = Observer<FirebaseUser> { user ->
            firebaseUser = user
            if(firebaseUser != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.userLiveData.observe(this, observer)


        val getAction =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it?.data)

                val account = task.getResult(ApiException::class.java)!!

                if(viewModel.firebaseAuthWithGoogle(account.idToken!!) != null){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }



        binding.signInWithGoogleBtn.setOnClickListener {
            getAction.launch(viewModel.getGoogleSignInGoogle().signInIntent)
        }
    }
}

