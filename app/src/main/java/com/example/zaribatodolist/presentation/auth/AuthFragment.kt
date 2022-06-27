package com.example.zaribatodolist.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.zaribatodolist.MainActivity
import com.example.zaribatodolist.databinding.FragmentAuthBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    private val viewModel: AuthViewModel by viewModels()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        /////////////// State Observer //////////////////////////////////
        val stateObserver = Observer<AuthUIState> { state ->
            if (!state.isLoading) {
                if (state.isAthenticated) {
                    binding.progressBar.visibility = View.GONE

                    val intent = Intent(activity, MainActivity::class.java)
                    activity?.startActivity(intent)
                }
            }
            if(state.isLoading){
                binding.progressBar.visibility = View.VISIBLE
            }
        }
        viewModel.uistate.observe(viewLifecycleOwner, stateObserver)
        /////////////////////////////////////////////////////////////////


        // Choose Google Account Action //////////////////////////////////////////////////////////
        val signInAction =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
                if (task.isSuccessful) {
                    val account = task.getResult(ApiException::class.java)!!
                    viewModel.firebaseAuthWithGoogle(account.idToken!!)
                }
            }

        if(auth.currentUser != null) {
            viewModel.getUserInfo(auth.currentUser!!.uid)
        }

        binding.signInWithGoogleBtn.setOnClickListener {
            if(auth.currentUser != null){
                viewModel.getUserInfo(auth.currentUser!!.uid)
            }else{
                signInAction.launch(viewModel.getGoogleSignIn().signInIntent)
            }
            }
        /////////////////////////////////////////////////////////////////////////////////////////////

        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)
}