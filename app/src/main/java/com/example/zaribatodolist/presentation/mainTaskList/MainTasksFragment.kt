package com.example.zaribatodolist.presentation.mainTaskList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.example.zaribatodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

import androidx.viewpager2.widget.ViewPager2
import com.example.zaribatodolist.R
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

        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.title = "Task List"

        binding.toolbar.setOnMenuItemClickListener{
            if(it.itemId == R.id.goToSignOutButton){
                Toast.makeText(context,
                    "This a toast message",
                    Toast.LENGTH_LONG);
                Navigation.findNavController(view).navigate(R.id.mainFragment_to_SignOut);
            }
            true
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tag", "Here")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksMainBinding = FragmentTasksMainBinding.inflate(inflater, container, false)
}