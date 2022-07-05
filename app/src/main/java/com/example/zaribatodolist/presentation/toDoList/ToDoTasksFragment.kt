package com.example.zaribatodolist.presentation.toDoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentTodoTasksBinding
import com.example.zaribatodolist.presentation.addTask.AddTaskFragment
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.mainTaskList.MainTasksFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ToDoTasksFragment : BaseFragment<FragmentTodoTasksBinding>() {


    private val viewModel: ToDoListViewModel by viewModels()
    private lateinit var myView: View
    private var tasksList = ArrayList<TaskModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        myView = binding.root

        binding.addBtn.setOnClickListener {
            val addTaskFragment = AddTaskFragment()
            addTaskFragment.show(activity!!.supportFragmentManager, "Add New Task")
        }

        binding.tasksRv.layoutManager = LinearLayoutManager(context)
        val adapter = TasksAdapter(tasksList,
            { id -> onCheckBoxListItemClick(id) },
            { task -> onCardViewClick(task) })

        binding.tasksRv.adapter = adapter

        viewModel.liveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, it.size.toString(), Toast.LENGTH_SHORT).show()
//            val list =
//                it.filter {
//                    it.isCompleted == false
//                } as ArrayList<TaskModel> /* = java.util.ArrayList<com.example.zaribatodolist.data.model.TaskModel> */
//            adapter.bindList(list)
            viewModel.handleDataChange()
        })

        viewModel.uistate.observe(viewLifecycleOwner, {
            Toast.makeText(context, "I am in life", Toast.LENGTH_SHORT).show()
            adapter.bindList(it.taskList!!)
        })

        return myView
    }

    private fun onCheckBoxListItemClick(id: String) {
        viewModel.handleCheckBoxClick(id)
    }

    private fun onCardViewClick(task: TaskModel) {

        Navigation.findNavController(myView)
            .navigate(MainTasksFragmentDirections.mainToDetail(task))
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoTasksBinding = FragmentTodoTasksBinding.inflate(inflater, container, false)
}