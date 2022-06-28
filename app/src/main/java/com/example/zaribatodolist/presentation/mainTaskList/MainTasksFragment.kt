package com.example.zaribatodolist.presentation.mainTaskList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels

import com.example.zaribatodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

import androidx.viewpager2.widget.ViewPager2
import com.example.zaribatodolist.databinding.FragmentTasksMainBinding
import com.google.android.material.tabs.TabLayoutMediator

@AndroidEntryPoint
class MainTasksFragment : BaseFragment<FragmentTasksMainBinding>() {

    private val viewModel : TaskListViewModel by viewModels()
    private lateinit var mPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        val adapter = ViewPagerAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0 -> tab.text = "To Do"
                else -> tab.text = "Completed"
            }
        }.attach()

        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksMainBinding = FragmentTasksMainBinding.inflate(inflater, container, false)
}