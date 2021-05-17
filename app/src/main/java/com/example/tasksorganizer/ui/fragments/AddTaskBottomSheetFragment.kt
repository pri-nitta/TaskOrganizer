package com.example.tasksorganizer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksorganizer.R
import com.example.tasksorganizer.data.models.Task
import com.example.tasksorganizer.ui.viewmodel.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_task_bottom_sheet.*

class AddTaskBottomSheetFragment: BottomSheetDialogFragment() {

    private var title =""
    private var description = ""
    private var status = ""

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_task_bottom_sheet, container, false)
    }

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
            Toast.makeText(context,"Please add at least a title", Toast.LENGTH_SHORT).show()
        } else{
            val task = Task(0, title, description, status)
            taskViewModel.addTask(task)
            Toast.makeText(context, "Task added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addTaskBottomSheetFragment_to_taskListFragment)
        }
    }
}
