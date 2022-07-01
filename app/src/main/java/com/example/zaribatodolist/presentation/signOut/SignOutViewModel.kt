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

    val liveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks

//    fun getUserInfo(uid: String) {
//        //uistate.value = SignOutUIState(true, false)
//        if(getUserInfoFromStorage.invoke() == null){
//            Log.i("Error", "Some problem with user")
//            signOut()
//        }else{
//            _successGetUserInfo.postValue(getUserInfoFromStorage.invoke())
//            uistate.value = SignOutUIState(false, false)
//        }
//    }

    fun signOut() {
        signOutUseCase.invoke()
        uistate.value = SignOutUIState(true, true)
    }
}