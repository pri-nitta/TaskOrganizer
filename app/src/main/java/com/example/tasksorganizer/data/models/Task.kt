package com.example.tasksorganizer.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskStatus: String,
    val taskTitle: String,
    val taskDescription: String
        ): Parcelable