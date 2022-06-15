package com.example.zaribatodolist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.data.repository.AuthRepository
import com.example.zaribatodolist.databinding.FragmentUserProfileBinding


class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = UserProfileViewModelFactory(AuthRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserProfileViewModel::class.java)
        viewModel.apply {

            nameLive.observe(viewLifecycleOwner) { text ->
                binding.userNameTV.text = text
            }

        }

        binding.button.setOnClickListener {
            viewModel.setUserName(binding.editTextTextPersonName.text.toString())
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}