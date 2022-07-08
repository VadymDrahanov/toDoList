package com.example.zaribatodolist.presentation.completedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentComletedTasksBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.sharedViewModel.SharedViewModel
import com.example.zaribatodolist.presentation.toDoList.TasksAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompletedTasksFragment : BaseFragment<FragmentComletedTasksBinding>() {

    private val viewModel: CompletedListViewModel by viewModels()
    private var tasksList = ArrayList<TaskModel>()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        val view = binding.root


        binding.tasksRv.layoutManager = LinearLayoutManager(context)
        val adapter = TasksAdapter(tasksList,
            { id -> onCheckBoxClick(id) },
            { task -> onCardViewClick(task) },
            { id -> onCardViewLongClick(id) })

        binding.tasksRv.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                adapter.bindList(it.taskList)
            }
        }

        viewModel.bindObservable()

        //        viewModel.tasksLiveData.observe(viewLifecycleOwner) {
//            viewModel.handleDataChanged()
//        }
//        viewModel.listLiveData.observe(viewLifecycleOwner) {
//            viewModel.handleDataChanged()
//        }

        return view
    }

    private fun onCardViewLongClick(task: TaskModel) {
        sharedViewModel.select(task)
    }

    private fun onCheckBoxClick(id: String) {
        //do nothing
    }

    private fun onCardViewClick(task: TaskModel) {
        //do nothing
        //viewModel.handleCheckBoxClick(id)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComletedTasksBinding =
        FragmentComletedTasksBinding.inflate(inflater, container, false)
}