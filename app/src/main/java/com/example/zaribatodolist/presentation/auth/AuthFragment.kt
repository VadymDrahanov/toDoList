package com.example.zaribatodolist.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.zaribatodolist.MainActivity
import com.example.zaribatodolist.databinding.FragmentAuthBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class AuthFragment : BaseFragment<AuthViewModel, FragmentAuthBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = getFragmentBinding(inflater, container)
        val view = binding.root

        viewModel.getFirebaseUser()

        val getAction =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it?.data)

                val account = task.getResult(ApiException::class.java)!!

                if (viewModel.firebaseAuthWithGoogle(account.idToken!!) != null) {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        binding.signInWithGoogleBtn.setOnClickListener {
            getAction.launch(viewModel.getGoogleSignInGoogle().signInIntent)
        }

        return view
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)
}