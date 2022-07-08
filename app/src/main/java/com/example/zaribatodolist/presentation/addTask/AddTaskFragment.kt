package com.example.zaribatodolist.presentation.addTask


import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.example.zaribatodolist.R
import com.example.zaribatodolist.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.annotation.Nullable

@AndroidEntryPoint
class AddTaskFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddNewTaskViewModel by viewModels()
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleEditText.requestFocus()
        val imm: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.titleEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.titleEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length > 0) {
                    binding.addTaskFAB.setBackgroundTintList(
                        ColorStateList.valueOf(
                            resources.getColor(
                                R.color.purple_light
                            )
                        )
                    );

                    binding.addTaskFAB.isEnabled = true
                } else {
                    binding.addTaskFAB.setBackgroundTintList(
                        ColorStateList.valueOf(
                            resources.getColor(
                                R.color.gray
                            )
                        )
                    );
                    binding.addTaskFAB.isEnabled = false
                }
            }
        })

        binding.addTaskFAB.setOnClickListener {
            viewModel.handleAddNewTask(
                binding.titleEditText.text.toString(),
                auth.currentUser!!.uid
            )
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            when {
                it.isCompleted -> {
                    binding.titleEditText.text.clear()
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}