package com.example.zaribatodolist.presentation.taskDetails

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.zaribatodolist.databinding.FragmentTaskDetailsBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.getTask(amount)

        viewModel.tasksLiveData.observe(viewLifecycleOwner) {
            binding.CheckBoxDetails.isChecked = it.isCompleted
            binding.taskNameDetails.text = it.title
            binding.editTextNotes.setText(it.note)
        }

        binding.backbtn.setOnClickListener {
            Navigation.findNavController(view)
                .popBackStack();
        }

        binding.confirmButtonID.setOnClickListener {
            viewModel.handleConfirmButtonClick(
                binding.editTextNotes.text.toString(),
                binding.CheckBoxDetails.isChecked
            )
        }

//        binding.editTextNotes.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(s: Editable) {
//                viewModel.handleNoteChange(s.toString())
//            }
//
//            override fun beforeTextChanged(
//                s: CharSequence, start: Int,
//                count: Int, after: Int
//            ) {
//            }
//
//            override fun onTextChanged(
//                s: CharSequence, start: Int,
//                before: Int, count: Int
//            ) {
//            }
//        })
//
//        binding.CheckBoxDetails.setOnClickListener {
//            if (!binding.CheckBoxDetails.isChecked) {
//                viewModel.handleCheckBoxClick()
//            }
//        }
        return view
    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskDetailsBinding = FragmentTaskDetailsBinding.inflate(inflater, container, false)

}