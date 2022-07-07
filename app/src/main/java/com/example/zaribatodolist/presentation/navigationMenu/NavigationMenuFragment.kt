package com.example.zaribatodolist.presentation.navigationMenu

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.databinding.FragmentNavigationMenuBinding
import com.example.zaribatodolist.presentation.base.BaseFragment
import com.example.zaribatodolist.presentation.dialog.CustomAlertDialogWithTextField
import com.example.zaribatodolist.presentation.toDoList.TasksAdapter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationMenuFragment : BaseFragment<FragmentNavigationMenuBinding>(),
    SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private val viewModel: NavigationMenuViewModel by viewModels()
    private lateinit var myView: View
    private lateinit var tasksRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = getFragmentBinding(inflater, container)
        myView = binding.root
        val toolbar = binding.navigationToolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        binding.tasksRv.layoutManager = LinearLayoutManager(context)
        tasksRv = binding.tasksRv

        //search observer
        viewModel.tasksLiveData.observe(viewLifecycleOwner) {
            val customAdapter =
                TasksAdapter(
                    it,
                    { id -> onListItemClick(id) },
                    { task -> onCardViewClick(task) },
                    { task -> onCardViewLongClick(task) })
            tasksRv.adapter = customAdapter
        }

        //ui state observer
        viewModel.uistate.observe(viewLifecycleOwner) {
            when (it.isSearching) {
                false -> {
                    binding.tasksRv.visibility = View.GONE
                    binding.layout.visibility = View.VISIBLE
                }

                true -> {
                    binding.tasksRv.visibility = View.VISIBLE
                    binding.layout.visibility = View.GONE
                }
            }
        }

        viewModel.listsLiveData.observe(viewLifecycleOwner) {
            val customAdapter = CollectionRVAdapter(it, { id -> onViewClick(id) })
            binding.collectionRV.layoutManager = LinearLayoutManager(context)
            binding.collectionRV.adapter = customAdapter
        }

        binding.goToTasksLayout.setOnClickListener {
            viewModel.handleListItemClick("main")
            Navigation.findNavController(myView).navigate(R.id.navigationToMain)
        }

        binding.addBtn.setOnClickListener {
            launchCustomAlertDialog()
        }
        return myView
    }

    private fun onViewClick(collectionID: String) {
        viewModel.handleListItemClick(collectionID)
        Navigation.findNavController(myView).navigate(R.id.navigationToMain)
    }

    private fun onListItemClick(id: String) {
        //do nothing
    }

    private fun onCardViewLongClick(id: TaskModel) {
        //do nothing
    }

    private fun onCardViewClick(task: TaskModel) {
        Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
        //viewModel.handleCheckBoxClick(id)
        Navigation.findNavController(myView)
            .navigate(
                NavigationMenuFragmentDirections.navigationToDetails(task)
            )

    }

    lateinit var dialog: CustomAlertDialogWithTextField

    private fun launchCustomAlertDialog() {
        dialog = CustomAlertDialogWithTextField(requireContext())
        dialog.setTitle("New List")



        dialog.setOnPositiveBtnClickListener {
            val title: String = dialog.getTextFieldResult()
            if (!title.isBlank() || title != " ") {
                viewModel.handleAddNewList(
                    title,
                    FirebaseAuth.getInstance().currentUser!!.uid.toString()
                )
            }
        }

        dialog.show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        searchView?.setOnCloseListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavigationMenuBinding =
        FragmentNavigationMenuBinding.inflate(inflater, container, false)

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.handleSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.handleSearch(newText)

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onClose()
    }

    override fun onClose(): Boolean {
        viewModel.searchClosed()
        return true
    }

}