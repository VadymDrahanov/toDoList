package com.example.zaribatodolist.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.zaribatodolist.MainActivity
import com.example.zaribatodolist.databinding.FragmentAuthBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.base.UIState
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        val stateObserver = Observer<AuthUIState> { state ->
            if(state.isLoading){
                Toast.makeText(activity, "i am here", Toast.LENGTH_SHORT).show()
            }
            if (!state.isLoading) {
                if (state.isAthenticated) {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity?.startActivity(intent)
                }
            }
        }
        viewModel.uistate.observe(viewLifecycleOwner, stateObserver)

        val signInAction =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
                if (task.isSuccessful) {
                    val account = task.getResult(ApiException::class.java)!!
                    viewModel.firebaseAuthWithGoogle(account.idToken!!)
                }
            }

        binding.signInWithGoogleBtn.setOnClickListener {
            signInAction.launch(viewModel.getGoogleSignInGoogle().signInIntent)
        }

        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)
}