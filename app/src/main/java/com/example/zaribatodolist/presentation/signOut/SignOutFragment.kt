package com.example.zaribatodolist.presentation.signOut

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.zaribatodolist.R
import com.example.zaribatodolist.databinding.FragmentSignOutBinding
import com.example.zaribatodolist.presentation.auth.AuthActivity
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignOutFragment : BaseFragment<FragmentSignOutBinding>() {

    private val viewModel: SignOutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        val user = FirebaseAuth.getInstance().currentUser
        binding.userName.text = user!!.displayName
        binding.userEmail.text = user.email
        Picasso.get().load(user.photoUrl).into(binding.userImage);

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
        viewModel.uiState.observe(viewLifecycleOwner, stateObserver)

        binding.signOutButton.setOnClickListener {
            viewModel.signOut()
        }

        binding.closeSignOutFragment.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.signOutToMain);
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignOutBinding = FragmentSignOutBinding.inflate(inflater, container, false)

}