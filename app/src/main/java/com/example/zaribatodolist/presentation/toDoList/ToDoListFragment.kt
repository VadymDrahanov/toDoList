package com.example.zaribatodolist.presentation.toDoList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListFragment : Fragment() {

    private lateinit var viewModel: ToDoListViewModel

    private lateinit var tasksRecycleVIew : RecyclerView
    private lateinit var arrayList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_todo_tasks, container, false)
    }


}