package com.example.zaribatodolist.presentation.toDoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.TaskModel


class TasksAdapter(
    private val dataSet: ArrayList<TaskModel>,
    private val onCheckBoxClick: (id: String) -> Unit,
    private val onCardViewClick: (task: TaskModel) -> Unit
) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_task, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        if (dataSet.get(position).isCompleted) {
            viewHolder.titleCheckBox.isChecked = true
            viewHolder.titleCheckBox.isEnabled = false
        }

        viewHolder.titleCheckBox.text = dataSet.get(position).title

        viewHolder.titleCheckBox.setOnClickListener {
            onCheckBoxClick(dataSet.get(position).uid)
        }

        viewHolder.main_layout.setOnClickListener {
            onCardViewClick(dataSet.get(position))
        }
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleCheckBox = itemView.findViewById<CheckBox>(R.id.checkBoxTaskName)
        val starCheckBox = itemView.findViewById<CheckBox>(R.id.starCheckBox)
        val main_layout = itemView.findViewById<ConstraintLayout>(R.id.taskItemCL)
    }
}