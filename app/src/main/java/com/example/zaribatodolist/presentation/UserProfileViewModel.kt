package com.example.zaribatodolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.data.repository.AuthRepository

class UserProfileViewModel(private val repository: AuthRepository) : ViewModel() {

    val nameLive = MutableLiveData<String>()

    fun setUserName(name: String) {
        nameLive.value = repository.getString(name)
    }

}

class UserProfileViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepository::class.java).newInstance(repository)
    }
}