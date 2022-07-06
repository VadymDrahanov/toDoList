package com.example.zaribatodolist.presentation.sharedViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zaribatodolist.data.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val mutableSelectedItem = MutableLiveData<ArrayList<TaskModel>>()
    val selectedItem: LiveData<ArrayList<TaskModel>> get() = mutableSelectedItem

    fun select(item: TaskModel) {
        if (mutableSelectedItem.value == null) {
            mutableSelectedItem.value = ArrayList<TaskModel>()
        }

        val temp = mutableSelectedItem.value

        if (temp?.contains(item) == true) {
            temp.remove(item)
        } else {
            temp?.add(item)
        }
        
        mutableSelectedItem.value = temp

    }
}