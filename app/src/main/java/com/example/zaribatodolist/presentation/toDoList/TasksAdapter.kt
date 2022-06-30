package com.example.zaribatodolist.presentation.toDoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.TaskModel


class TasksAdapter(private val dataSet: ArrayList<TaskModel>) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_task, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}