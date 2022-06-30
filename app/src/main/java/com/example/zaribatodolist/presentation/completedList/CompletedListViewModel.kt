package com.example.zaribatodolist.presentation.completedList

import androidx.lifecycle.LiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.TasksObserverUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompletedListViewModel @Inject constructor(
    tasksObserverUseCase: TasksObserverUseCase
) :
    BaseViewModel<CompletedUIState>() {
    val liveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks
}