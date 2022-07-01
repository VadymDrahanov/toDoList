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
    private lateinit var myView : View

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

        viewModel.liveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, "I am called", Toast.LENGTH_SHORT).show()

            val list =
                it.filter {
                    it.isCompleted == false
                }
            val customAdapter =
                TasksAdapter(list as ArrayList<TaskModel>, { id -> onCheckBoxListItemClick(id)}, {task -> onCardViewClick(task)})
            binding.tasksRv.adapter = customAdapter
        })

        return myView
    }

    private fun onCheckBoxListItemClick(id: String) {
        //Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
        viewModel.handleCheckBoxClick(id)
    }

    private fun onCardViewClick(task: TaskModel) {
        //Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
        //viewModel.handleCheckBoxClick(id)
        //Navigation.findNavController(myView).navigate(R.id.action_mainFragment_to_taskDetailsFragment);
        Navigation.findNavController(myView)
            .navigate(MainTasksFragmentDirections.actionMainFragmentToTaskDetailsFragment(task))
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoTasksBinding = FragmentTodoTasksBinding.inflate(inflater, container, false)
}