package com.example.zaribatodolist.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.zaribatodolist.MainActivity
import com.example.zaribatodolist.R
import com.example.zaribatodolist.databinding.FragmentAuthBinding
import com.example.zaribatodolist.databinding.FragmentUserProfileBinding
import com.example.zaribatodolist.presentation.user.UserProfileViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = CustomViewModelFactory().getViewModel("aa", requireContext()) as AuthViewModel
        viewModel.getFirebaseUser()

        val getAction =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it?.data)

                val account = task.getResult(ApiException::class.java)!!

                if(viewModel.firebaseAuthWithGoogle(account.idToken!!) != null){
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        binding.signInWithGoogleBtn.setOnClickListener {
            getAction.launch(viewModel.getGoogleSignInGoogle().signInIntent
            )
        }
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}