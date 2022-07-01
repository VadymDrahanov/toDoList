package com.example.zaribatodolist.presentation.taskDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.zaribatodolist.R
import com.example.zaribatodolist.databinding.FragmentTaskDetailsBinding
import com.example.zaribatodolist.databinding.FragmentTodoTasksBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.toDoList.ToDoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TaskDetailsFragment : BaseFragment<FragmentTaskDetailsBinding>() {

    val args: TaskDetailsFragmentArgs by navArgs()
    private val viewModel: TaskDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getFragmentBinding(inflater, container)
        val view = binding.root
        val amount = args.taskModel
        viewModel.getTask(amount.uid)

        viewModel.tasksLiveData.observe(viewLifecycleOwner, {
            binding.checkBoxTaskName.isChecked = it.isCompleted
            binding.checkBoxTaskName.text = it.title
            binding.editTextNotes.setText(it.note)
        })

        binding.backbtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_taskDetailsFragment_to_mainFragment);
        }

        return view
    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskDetailsBinding = FragmentTaskDetailsBinding.inflate(inflater, container, false)

}