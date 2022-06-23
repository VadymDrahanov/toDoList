package com.example.zaribatodolist.presentation.signOut

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.usecase.GetUserInfoUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel<SignOutUIState>() {
    val nameEmpty = MutableLiveData<Unit>()

    private val _successGetUserInfo = MutableLiveData<User?>()
    val successGetUserInfo = _successGetUserInfo as LiveData<User?>

    fun getUserInfo(uid: String) {
        viewModelScope.launch {
            try {
                getUserInfoUseCase.invoke(uid = uid)?.addOnCompleteListener {
                    when {
                        it.isSuccessful -> {
                            it.addOnSuccessListener { documentSnapshot ->
                                _successGetUserInfo.postValue(documentSnapshot.toObject<User?>())
                            }
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}