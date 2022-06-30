package com.example.zaribatodolist.presentation.completedList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentComletedTasksBinding
import com.example.zaribatodolist.databinding.FragmentTodoTasksBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.toDoList.TasksAdapter
import com.example.zaribatodolist.presentation.toDoList.ToDoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompletedTasksFragment : BaseFragment<FragmentComletedTasksBinding>() {

    private val viewModel: CompletedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        binding.tasksRv.layoutManager = LinearLayoutManager(context)

        viewModel.liveData.observe(viewLifecycleOwner, {
            var list =
                it.filter {
                    it.isCompleted == true
                }
            val customAdapter = TasksAdapter(list as ArrayList<TaskModel>)
            binding.tasksRv.adapter = customAdapter
        })

        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComletedTasksBinding = FragmentComletedTasksBinding.inflate(inflater, container, false)
}