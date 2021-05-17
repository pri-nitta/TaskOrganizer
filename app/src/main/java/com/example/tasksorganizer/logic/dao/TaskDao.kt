package com.example.tasksorganizer.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasksorganizer.data.models.Task

@Dao
interface TaskDao {

    @Insert(onConflict = onConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun editTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<Task>>

}