package com.example.zaribatodolist.presentation.completedList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zaribatodolist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComletedTasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comleted_tasks, container, false)
    }
}