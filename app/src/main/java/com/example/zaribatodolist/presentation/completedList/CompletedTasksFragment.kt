package com.example.zaribatodolist.presentation.completedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentComletedTasksBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.toDoList.TasksAdapter
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

        viewModel.tasksLiveData.observe(viewLifecycleOwner, {
            var list =
                it.filter {
                    it.isCompleted == true
                }
            val customAdapter =
                TasksAdapter(
                    list as ArrayList<TaskModel>,
                    { id -> onListItemClick(id) },
                    { task -> onCardViewClick(task) })

            binding.tasksRv.adapter = customAdapter
        })

        return view
    }

    private fun onListItemClick(id: String) {
        Toast.makeText(context, "this, mRepos[position].name", Toast.LENGTH_SHORT).show()

    }

    private fun onCardViewClick(task: TaskModel) {
        Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
        //viewModel.handleCheckBoxClick(id)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComletedTasksBinding =
        FragmentComletedTasksBinding.inflate(inflater, container, false)
}