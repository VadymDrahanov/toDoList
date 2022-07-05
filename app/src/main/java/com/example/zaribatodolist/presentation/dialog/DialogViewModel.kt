package com.example.zaribatodolist.presentation.dialog

import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor() : BaseViewModel<DialogFragmentUIState>(){

    fun initDialog(mode: String){
        if(mode.equals("Delete Task")){
            uistate.value = DialogFragmentUIState(isRemoveTaskDialog = true)
        }
        if(mode.equals("Add New Task")){
            uistate.value = DialogFragmentUIState(isNewListDialog = true)
        }
    }
}