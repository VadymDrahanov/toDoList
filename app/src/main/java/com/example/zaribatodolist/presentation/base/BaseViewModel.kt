package com.example.zaribatodolist.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

     lateinit var authState: MutableLiveData<String>

}