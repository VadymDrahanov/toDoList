package com.example.zaribatodolist.presentation.signOut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.zaribatodolist.databinding.FragmentSignOutBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
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
        binding.userEmail.text = "a;ls"

        viewModel.successGetUserInfo.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            it?.let {
            }
        })
        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignOutBinding = FragmentSignOutBinding.inflate(inflater, container, false)
}