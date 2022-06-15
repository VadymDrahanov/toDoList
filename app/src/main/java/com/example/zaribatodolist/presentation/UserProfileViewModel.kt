package com.example.zaribatodolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.data.repository.AuthRepository

class UserProfileViewModel(private val arg: Int, private val repository: AuthRepository) : ViewModel() {

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

    fun isWorking(){
        repository.isWorking()
    }
}

class UserProfileViewModelFactory(private val arg: Int,private val repository: AuthRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Int::class.java, AuthRepository::class.java).newInstance(arg, repository)
    }
}