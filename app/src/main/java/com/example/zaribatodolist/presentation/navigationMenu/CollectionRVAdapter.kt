package com.example.zaribatodolist.presentation.navigationMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.ListModel

class CollectionRVAdapter(
    private val objectsList: ArrayList<ListModel>,
    private val onViewClick: (id: String) -> Unit
) :
    RecyclerView.Adapter<CollectionRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collection, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = objectsList.get(position).title

        holder.layout.setOnClickListener {
            onViewClick(objectsList.get(position).id)
        }
    }

    override fun getItemCount(): Int {
        return objectsList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout = itemView.findViewById<ConstraintLayout>(R.id.collectionCL)
        val title = itemView.findViewById<TextView>(R.id.collectionTitle)
    }
}