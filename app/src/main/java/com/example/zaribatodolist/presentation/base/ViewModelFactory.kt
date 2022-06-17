package com.example.zaribatodolist.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.data.repository.AuthRepository
import com.example.zaribatodolist.presentation.auth.AuthViewModel
import com.example.zaribatodolist.presentation.fragmentHolder.HolderViewModel
import com.example.zaribatodolist.presentation.user.UserProfileViewModel

class ViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(repository) as T
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) ->
                UserProfileViewModel(repository) as T
            modelClass.isAssignableFrom(HolderViewModel::class.java) ->
                HolderViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}