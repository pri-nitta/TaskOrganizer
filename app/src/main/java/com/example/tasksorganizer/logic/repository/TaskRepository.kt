package com.example.tasksorganizer.logic.repository

import androidx.lifecycle.LiveData
import com.example.tasksorganizer.data.models.Task
import com.example.tasksorganizer.logic.dao.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val getAllTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun editTask(task: Task){
        taskDao.editTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}