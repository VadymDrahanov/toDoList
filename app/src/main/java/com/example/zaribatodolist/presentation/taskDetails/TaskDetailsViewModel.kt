package com.example.zaribatodolist.presentation.taskDetails

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.taskrepo.GetTasksFromStorage
import com.example.zaribatodolist.domain.usecase.taskrepo.UpdateTaskCompletionUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.UpdateTaskNoteUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val getTasksFromStorage: GetTasksFromStorage,
    private val updateTaskNoteUseCase: UpdateTaskNoteUseCase,
    private val updateTaskCompletionUseCase: UpdateTaskCompletionUseCase
) :
    BaseViewModel<TaskDetailsUIState>() {

    val tasksLiveData: MutableLiveData<TaskModel> = MutableLiveData()

    fun getTask(task: TaskModel) {
        tasksLiveData.value = task
//        val res = getTasksFromStorage.invoke(id)
//        if (res != null) {
//            tasksLiveData.value = res
//        }
    }

    private fun handleCheckBoxClick() {
        updateTaskCompletionUseCase.invoke(id = tasksLiveData.value!!.uid)
    }

    fun handleConfirmButtonClick(noteText: String, confirmationState: Boolean) {
        if (!noteText.equals(tasksLiveData.value?.note)) {
            handleNoteChange(noteText)
        }
        if (confirmationState != tasksLiveData.value?.isCompleted) {
            handleCheckBoxClick()
        }
    }

    private fun handleNoteChange(note: String) {
        if (!note.equals(tasksLiveData.value!!.note)) {
            updateTaskNoteUseCase.invoke(newNote = note, id = tasksLiveData.value!!.uid)
        }
    }
}