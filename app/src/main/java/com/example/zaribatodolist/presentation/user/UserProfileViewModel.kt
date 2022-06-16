package com.example.zaribatodolist.presentation.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zaribatodolist.data.repository.AuthRepository

class UserProfileViewModel(private val repository: AuthRepository) : ViewModel() {

    val nameLive = MutableLiveData<String>()

    fun setUserName() {
        nameLive.value = "John Doe"
    }


}

