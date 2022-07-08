package com.example.zaribatodolist.presentation.toDoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentTodoTasksBinding
import com.example.zaribatodolist.presentation.addTask.AddTaskFragment
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.mainTaskList.MainTasksFragmentDirections
import com.example.zaribatodolist.presentation.sharedViewModel.SharedViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoTasksFragment : BaseFragment<FragmentTodoTasksBinding>() {

    private val viewModel: ToDoListViewModel by viewModels()
    private lateinit var myView: View
    private var tasksList = ArrayList<TaskModel>()

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        myView = binding.root

        binding.addBtn.setOnClickListener {
            val addTaskFragment = AddTaskFragment()
            addTaskFragment.show(requireActivity().supportFragmentManager, "Add New Task")
        }

        binding.tasksRv.layoutManager = LinearLayoutManager(context)
        val adapter = TasksAdapter(tasksList,
            { id -> onCheckBoxListItemClick(id) },
            { task -> onCardViewClick(task) },
            { task -> onCardViewLongClick(task) })

        binding.tasksRv.adapter = adapter

//        viewModel.tasksLiveData.observe(viewLifecycleOwner) {
//            //Toast.makeText(requireContext(), "I am here", Toast.LENGTH_SHORT).show()
//            viewModel.handleListChange()
//        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                //binding.progressBar.visibility = View.VISIBLE
            } else {
                //binding.progressBar.visibility = View.INVISIBLE
                adapter.bindList(it.taskList)
            }
        }

        viewModel.bindObservable()

        binding.addBtn.setOnLongClickListener() {
            //viewModel.addTask(generateTaskModel())
            //Toast.makeText(requireContext(), "Msg", Toast.LENGTH_SHORT).show()
            true
        }

//        viewModel.currentList.observe(viewLifecycleOwner) {
//            viewModel.handleListChange()
//        }

        return myView
    }



    private fun onCheckBoxListItemClick(id: String) {
        viewModel.handleCheckBoxClick(id)
    }

    private fun onCardViewLongClick(task: TaskModel) {
        sharedViewModel.select(task)
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