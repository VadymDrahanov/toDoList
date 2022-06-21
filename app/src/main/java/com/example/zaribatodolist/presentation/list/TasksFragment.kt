package com.example.zaribatodolist.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.databinding.FragmentTaskListBinding
import com.example.zaribatodolist.databinding.HolderFragmentBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : BaseFragment<HolderFragmentBinding>() {

    private val viewModel : TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        viewModel.getUser().observe(viewLifecycleOwner , Observer<User>{
            binding.textView2.text = it.name
        } )

        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HolderFragmentBinding = HolderFragmentBinding.inflate(inflater, container, false)

}