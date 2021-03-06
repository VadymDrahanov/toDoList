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
    private var tasks: List<TaskModel>,
    private val onCheckBoxClick: (id: String) -> Unit,
    private val onCardViewClick: (task: TaskModel) -> Unit,
    private val onCardViewLongClick: (id: TaskModel) -> Unit
) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_task, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.titleCheckBox.isChecked = false

        if (tasks.get(position).isCompleted) {
            viewHolder.starCheckBox.isChecked = true
            viewHolder.starCheckBox.isEnabled = false
        } else {
            viewHolder.starCheckBox.isChecked = false
            viewHolder.starCheckBox.isEnabled = true
        }

        viewHolder.titleCheckBox.text = tasks.get(position).title

        viewHolder.starCheckBox.setOnClickListener {
            onCheckBoxClick(tasks.get(position).uid)
        }

        viewHolder.main_layout.setOnClickListener {
            onCardViewClick(tasks.get(position))
        }

        viewHolder.titleCheckBox.setOnClickListener {
            onCardViewLongClick(tasks[position])
        }
    }

    fun bindList(list: List<TaskModel>) {
        tasks = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = tasks.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleCheckBox = itemView.findViewById<CheckBox>(R.id.checkBoxTaskName)
        val starCheckBox = itemView.findViewById<CheckBox>(R.id.starCheckBox)
        val main_layout = itemView.findViewById<ConstraintLayout>(R.id.taskItemCL)
    }
}