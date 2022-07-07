package com.example.zaribatodolist.presentation.mainTaskList

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.zaribatodolist.databinding.FragmentDialogBinding
import com.example.zaribatodolist.databinding.FragmentTasksMainBinding
import com.example.zaribatodolist.presentation.dialog.AlertDialogConfiguration
import com.example.zaribatodolist.presentation.dialog.CustomAlertDialog
import com.example.zaribatodolist.presentation.sharedViewModel.SharedViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

@AndroidEntryPoint
class MainTasksFragment : BaseFragment<FragmentTasksMainBinding>() {

    private val viewModel: MainTasksViewModel by viewModels()
    private val shareModel: SharedViewModel by activityViewModels()
    private var listOfSelectedItems = ArrayList<TaskModel>()


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


        viewModel.uistate.observe(viewLifecycleOwner) {
            when {
                it.isShareProcess -> {
                    launchCustomAlertDialog()
                }
            }
        }

        return view
    }
    lateinit var dialog : CustomAlertDialog
    private fun launchCustomAlertDialog() {
        dialog = CustomAlertDialog(requireContext()) { onPositiveDialogButtonClick() }
        dialog.show()

    }

    private fun onPositiveDialogButtonClick() {
        Log.i("Caller", "i am here")
        viewModel.handleOnShareButtonClick(dialog.getTextFieldResult(), listOfSelectedItems.map { it.uid } as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksMainBinding = FragmentTasksMainBinding.inflate(inflater, container, false)
}