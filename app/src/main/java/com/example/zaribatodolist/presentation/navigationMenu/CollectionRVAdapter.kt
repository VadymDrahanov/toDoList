package com.example.zaribatodolist.presentation.navigationMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.zaribatodolist.R

class CollectionRVAdapter(private val objectsList: ArrayList<String>) : RecyclerView.Adapter<CollectionRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collection, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return objectsList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}