package com.example.zaribatodolist.presentation.toDoList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R
import com.example.zaribatodolist.databinding.FragmentSignOutBinding
import com.example.zaribatodolist.databinding.FragmentTodoTasksBinding
import com.example.zaribatodolist.presentation.addTask.AddTaskFragment
import com.example.zaribatodolist.presentation.auth.AuthViewModel
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoTasksFragment : BaseFragment<FragmentTodoTasksBinding>() {

    private lateinit var tasksRecycleVIew : RecyclerView
    private lateinit var arrayList: ArrayList<String>
    private val auth = FirebaseAuth.getInstance()


    private val viewModel: ToDoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        binding.addBtn.setOnClickListener{
            val addTaskFragment = AddTaskFragment()
            addTaskFragment.show(activity!!.supportFragmentManager, "Add New Task")
        }

        viewModel.getUserTasks(auth.currentUser!!.uid)
        return view
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoTasksBinding = FragmentTodoTasksBinding.inflate(inflater, container, false)
}