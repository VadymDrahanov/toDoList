package com.example.zaribatodolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserProfileViewModel(private val arg: Int) : ViewModel() {

    val nameLive = MutableLiveData<String>()

    val ageLive = MutableLiveData<Int>(0)

    fun setUserName() {
        nameLive.value = "John Doe"
    }

    fun setUserAge() {
        ageLive.value?.let {
            ageLive.value = it + arg
        }
    }
}

class UserProfileViewModelFactory(val arg: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Int::class.java).newInstance(arg)
    }
}