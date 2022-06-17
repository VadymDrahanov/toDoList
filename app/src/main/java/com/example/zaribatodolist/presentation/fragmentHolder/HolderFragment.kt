package com.example.zaribatodolist.presentation.fragmentHolder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.zaribatodolist.databinding.HolderFragmentBinding
import com.example.zaribatodolist.presentation.base.BaseFragment

class HolderFragment : BaseFragment<HolderViewModel, HolderFragmentBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container)
        val view = binding.root

        return view
    }

    override fun getViewModel(): Class<HolderViewModel> = HolderViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HolderFragmentBinding = HolderFragmentBinding.inflate(inflater, container, false)

}