package com.example.zaribatodolist.presentation.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider

import com.example.zaribatodolist.data.repository.AuthRepository
import com.example.zaribatodolist.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //refactor it
        val factory = AuthViewModelFactory(AuthRepository(applicationContext))

        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding.signInWithGoogleBtn.setOnClickListener {
            startActivityForResult(viewModel.getGoogleSignInGoogle().signInIntent, RC_SIGN_IN)
        }

        viewModel.getFirebaseUser()

    }
    //refactor it
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK && data != null) {
            // this task is responsible for getting ACCOUNT SELECTED
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!

                viewModel.firebaseAuthWithGoogle(account.idToken!!)

                Toast.makeText(this, "Signed In Successfully ${account.account?.name}", Toast.LENGTH_SHORT)
                    .show()

            } catch (e: ApiException) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object {
        private const val RC_SIGN_IN = 9001
    }
}

