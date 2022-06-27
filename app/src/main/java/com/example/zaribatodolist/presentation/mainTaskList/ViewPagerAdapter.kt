package com.example.zaribatodolist.presentation.mainTaskList

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zaribatodolist.presentation.completedList.ComletedTasksFragment
import com.example.zaribatodolist.presentation.toDoList.ToDoListFragment

class ViewPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return ToDoListFragment()
            else -> return ComletedTasksFragment()
        }
    }
}