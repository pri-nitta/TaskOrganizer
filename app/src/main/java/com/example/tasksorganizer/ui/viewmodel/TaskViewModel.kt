package com.example.tasksorganizer.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasksorganizer.data.database.TaskDataBase
import com.example.tasksorganizer.data.models.Task
import com.example.tasksorganizer.logic.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TaskRepository
    val getAllTasks: LiveData<List<Task>>

    init{
        val taskDao = TaskDataBase.getDataBase(application).taskDao()
        repository = TaskRepository(taskDao)
        getAllTasks = repository.getAllTasks
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }

    fun editTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.editTask(task)
        }
    }
}