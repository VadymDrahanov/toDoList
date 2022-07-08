package com.example.zaribatodolist.presentation.signOut

import androidx.lifecycle.LiveData
import com.example.zaribatodolist.data.model.TaskModel

import com.example.zaribatodolist.domain.usecase.userrepo.GetUserInfoFromStorage
import com.example.zaribatodolist.domain.usecase.userrepo.GetUserInfoUseCase
import com.example.zaribatodolist.domain.usecase.authrepo.SignOutUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getUserInfoFromStorage: GetUserInfoFromStorage,
    tasksObserverUseCase: ObservTasksUseCase

) : BaseViewModel<SignOutUIState>() {

    val liveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasksData


    fun signOut() {
        signOutUseCase.invoke()
        uiState.value = SignOutUIState(true, true)
    }
}