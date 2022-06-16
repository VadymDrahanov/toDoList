package com.example.zaribatodolist.presentation.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zaribatodolist.data.repository.AuthRepository
import com.example.zaribatodolist.presentation.user.UserProfileViewModel

public class CustomViewModelFactory {

    fun getViewModel(type: String, context: Context): ViewModel {

        val authRepository = AuthRepository(context)
        return when (type) {
            "auth" -> {
                AuthViewModel(authRepository)
            }
            "user_profile" -> {
                UserProfileViewModel(authRepository)
            }
            else -> {
                AuthViewModel(authRepository)
            }
        }
    }
}