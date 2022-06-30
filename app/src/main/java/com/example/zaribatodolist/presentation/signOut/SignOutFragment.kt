package com.example.zaribatodolist.presentation.signOut

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.zaribatodolist.MainActivity
import com.example.zaribatodolist.databinding.FragmentSignOutBinding
import com.example.zaribatodolist.presentation.auth.AuthActivity
import com.example.zaribatodolist.presentation.auth.AuthUIState
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignOutFragment : BaseFragment<FragmentSignOutBinding>() {

    private val viewModel: SignOutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        val view = binding.root
        viewModel.getUserInfo(FirebaseAuth.getInstance().currentUser!!.uid)

        viewModel.successGetUserInfo.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            it?.let {
                binding.userEmail.text = it.email
                binding.userName.text = it.name
                Picasso.get().load((it.photoUrl)).into(binding.imageView2);
            }
        })

        val stateObserver = Observer<SignOutUIState> { state ->
            if (!state.isLoading) {
                binding.progressBarSignOut.visibility = View.GONE
                binding.cardView.visibility = View.VISIBLE
            }
            if (state.isLoading) {
                binding.cardView.visibility = View.GONE
                binding.progressBarSignOut.visibility = View.VISIBLE
            }
            if(state.isSignOuted){
                val intent = Intent(activity, AuthActivity::class.java)
                activity?.startActivity(intent)
            }
        }

        viewModel.uistate.observe(viewLifecycleOwner, stateObserver)

        binding.signOutButton.setOnClickListener{
            viewModel.signOut()
        }

        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignOutBinding = FragmentSignOutBinding.inflate(inflater, container, false)
}