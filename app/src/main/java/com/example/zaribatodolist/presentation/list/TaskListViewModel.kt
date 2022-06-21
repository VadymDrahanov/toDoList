package com.example.zaribatodolist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.reposotory.AuthRepository
import com.example.zaribatodolist.domain.reposotory.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            //authRepository.getUser()
        }
    }

    fun getUser() : LiveData<User>{
        return user
    }
}