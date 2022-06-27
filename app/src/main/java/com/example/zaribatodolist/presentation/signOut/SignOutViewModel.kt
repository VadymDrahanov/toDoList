package com.example.zaribatodolist.presentation.signOut

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.usecase.GetUserInfoFromStorage
import com.example.zaribatodolist.domain.usecase.GetUserInfoUseCase
import com.example.zaribatodolist.domain.usecase.SignOutUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getUserInfoFromStorage: GetUserInfoFromStorage
) : BaseViewModel<SignOutUIState>() {

    private val _successGetUserInfo = MutableLiveData<User?>()
    val successGetUserInfo = _successGetUserInfo as LiveData<User?>

    fun getUserInfo(uid: String) {
        uistate.value = SignOutUIState(true, false)
        if(getUserInfoFromStorage.invoke() == null){
            Log.i("Error", "Some problem with user")
            signOut()
        }else{
            _successGetUserInfo.postValue(getUserInfoFromStorage.invoke())
            uistate.value = SignOutUIState(false, false)
        }

//        viewModelScope.launch {
//            try {
//                getUserInfoUseCase.invoke(uid = uid)?.addOnCompleteListener {
//                    when {
//                        it.isSuccessful -> {
//                            it.addOnSuccessListener { documentSnapshot ->
//                                uistate.value = SignOutUIState(false, false)
//
//                                val user = User(
//                                    uid = documentSnapshot.get("uid").toString(),
//                                    name = documentSnapshot.get("name").toString(),
//                                    tasks = documentSnapshot.get("tasks") as ArrayList<Task>,
//                                    email = documentSnapshot.get("email").toString(),
//                                    newUser = documentSnapshot.get("newUser") as Boolean,
//                                    photoUrl = Uri.parse(
//                                        (documentSnapshot.get("photoUrl").toString())
//                                    )
//                                )
//                                _successGetUserInfo.postValue(user)
//                            }
//                        }
//                    }
//                }
//            } catch (e: java.lang.Exception) {
//                e.printStackTrace()
//            }
//        }
    }

    fun signOut() {
        signOutUseCase.invoke()
        uistate.value = SignOutUIState(true, true)
    }
}