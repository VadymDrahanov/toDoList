package com.example.zaribatodolist.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.data.repository.AuthRepository

class UserProfileViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepository::class.java).newInstance(repository)
    }
}