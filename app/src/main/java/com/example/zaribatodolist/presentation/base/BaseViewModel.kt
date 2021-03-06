package com.example.zaribatodolist.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<UiState: UIState> : ViewModel() {

    var uiState: MutableLiveData<UiState> = MutableLiveData()

}