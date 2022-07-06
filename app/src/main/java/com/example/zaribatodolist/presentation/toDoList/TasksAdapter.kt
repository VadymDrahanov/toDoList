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
    private var tasks: ArrayList<TaskModel>,
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

        if (tasks.get(position).isCompleted) {
            viewHolder.titleCheckBox.isChecked = true
            viewHolder.titleCheckBox.isEnabled = false
        }else {
            viewHolder.titleCheckBox.isChecked = false
            viewHolder.titleCheckBox.isEnabled = true
        }

        viewHolder.titleCheckBox.text = tasks.get(position).title

        viewHolder.titleCheckBox.setOnClickListener {
            onCheckBoxClick(tasks.get(position).uid)
        }

        viewHolder.main_layout.setOnClickListener {
            onCardViewClick(tasks.get(position))
        }
//
//        viewHolder.main_layout.setOnLongClickListener {
//
//        }
    }

    fun bindList(list: ArrayList<TaskModel>) {
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