package com.example.zaribatodolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel : ViewModel() {

    val nameLive = MutableLiveData<String>()
    val ageLive = MutableLiveData<Int>()

    fun setUserName() {
        nameLive.value = "John Doe"
    }

    fun setUserAge() {
        ageLive.value = 32
    }

}