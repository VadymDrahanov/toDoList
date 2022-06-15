package com.example.zaribatodolist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.databinding.FragmentUserProfileBinding


class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        vm = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        vm.setUserName()
        vm.setUserAge()

        vm.nameLive.observe(viewLifecycleOwner, { text->
            binding.userNameTV.text = text
        })

        vm.ageLive.observe(viewLifecycleOwner, { text->
            binding.userAgeTV.text = text.toString()
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}