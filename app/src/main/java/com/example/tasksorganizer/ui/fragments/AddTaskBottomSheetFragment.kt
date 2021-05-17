package com.example.tasksorganizer.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksorganizer.R
import com.example.tasksorganizer.data.models.Task
import com.example.tasksorganizer.ui.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task_bottom_sheet.*

class AddTaskBottomSheetFragment : Fragment(R.layout.fragment_add_task_bottom_sheet) {

    private var title =""
    private var description = ""
    private var status = ""

    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        btnCreateTask.setOnClickListener{
            addTasktoDB()
        }
    }

    private fun addTasktoDB(){
        title = edtTaskTitle.text.toString()
        description = edtTaskDesc.text.toString()
        status = "to do"

        if (title.isEmpty()){
            error("Please type a title!")
        } else{
            val task = Task(0, title, description, status)
            taskViewModel.addTask(task)
            Toast.makeText(context, "Task added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addTaskBottomSheetFragment_to_taskListFragment)
        }
    }
}
