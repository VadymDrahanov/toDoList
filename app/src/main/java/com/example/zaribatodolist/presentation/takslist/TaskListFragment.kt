package com.example.zaribatodolist.presentation.takslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TaskListViewModel

    private lateinit var tasksRecycleVIew : RecyclerView
    private lateinit var arrayList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.tasksRv.layoutManager = LinearLayoutManager(requireContext())
        binding.tasksRv.setHasFixedSize(true)
        arrayList = arrayListOf<String>()
        arrayList.add("Salsdf")
        arrayList.add("Salsdf")
        arrayList.add("Salsdf")
        arrayList.add("Salsdf")

        binding.tasksRv.adapter = TasksAdapter(arrayList)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}