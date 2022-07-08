package com.example.zaribatodolist.presentation.mainTaskList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import com.example.zaribatodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentTasksMainBinding
import com.example.zaribatodolist.presentation.dialog.CustomAlertDialogWithTextField
import com.example.zaribatodolist.presentation.dialog.CustomDialog
import com.example.zaribatodolist.presentation.sharedViewModel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

@AndroidEntryPoint
class MainTasksFragment : BaseFragment<FragmentTasksMainBinding>() {

    private val viewModel: MainTasksViewModel by viewModels()
    private val shareModel: SharedViewModel by activityViewModels()
    private var listOfSelectedItems = ArrayList<TaskModel>()


    lateinit var dialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = getFragmentBinding(inflater, container)
        val view = binding.root

        val adapter = ViewPagerAdapter(requireActivity() as AppCompatActivity)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "To Do"
                else -> tab.text = "Completed"
            }
        }.attach()

        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.title = "To Do List"

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.goToSignOutButton) {
                Navigation.findNavController(view).navigate(R.id.mainFragment_to_SignOut);
            }
            if (it.itemId == R.id.removeItemsButton) {
                viewModel.removeTasks(listOfSelectedItems)
                listOfSelectedItems.clear()
            }
            if (it.itemId == R.id.shareTaskButton) {
                launchCustomAlertDialog()
            }



            true
        }

        shareModel.selectedItem.observe(viewLifecycleOwner, Observer<ArrayList<TaskModel>> {
            listOfSelectedItems = it
        })


        viewModel.uiState.observe(viewLifecycleOwner) {
            when {
                it.shareSuccess -> {
                    dialog.dismiss()
                }
                it.shareWentWrong -> {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong, try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                it.isProcess -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                !it.isProcess -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        return view
    }


    private fun launchCustomAlertDialog() {
//        dialog = CustomAlertDialogWithTextField(requireContext())
//        dialog.create()
//        dialog.getWindow()?.setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        dialog.setOnPositiveBtnClickListener {
//            Log.i("Caller", "i am here")
//            viewModel.handleOnShareButtonClick(
//                dialog.getTextFieldResult(),
//                listOfSelectedItems.map { it.uid } as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */)
//        }
//
//        dialog.setButtonText("Share")
//
//        dialog.show()

        dialog = CustomDialog(requireContext())
        dialog.setOnPositiveBtnClickListener {
            if (!it.isBlank() || it != " ") {


                viewModel.handleOnShareButtonClick(
                    it,
                    listOfSelectedItems.map { it.uid } as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */)

            }
        }


        dialog.show()


    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksMainBinding = FragmentTasksMainBinding.inflate(inflater, container, false)
}