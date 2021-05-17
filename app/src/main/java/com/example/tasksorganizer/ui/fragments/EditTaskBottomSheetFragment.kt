package com.example.tasksorganizer.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tasksorganizer.R
import com.example.tasksorganizer.data.models.Task
import com.example.tasksorganizer.ui.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_edit_task_bottom_sheet.*

class EditTaskBottomSheetFragment : Fragment(R.layout.fragment_edit_task_bottom_sheet) {
    private var title = ""
    private var description = ""
    private var status = ""

    private lateinit var taskViewModel: TaskViewModel
    private val args by navArgs<EditTaskBottomSheetFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        et_EditTaskTitle.setText(args.selectedTask.taskTitle)
        et_EditTaskDesc.setText(args.selectedTask.taskDescription)

        btnEditTask.setOnClickListener {
            editTask()
        }

        setHasOptionsMenu(true)
    }

    private fun editTask(){
        title = et_EditTaskTitle.text.toString()
        description = et_EditTaskDesc.text.toString()
        status = "to do"

        if (!(title.isEmpty())){
            val task = Task(args.selectedTask.id, title, description, status)

            taskViewModel.editTask(task)
            Toast.makeText(context, getString(R.string.task_add_correctly), Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_editTaskBottomSheetFragment_to_taskListFragment)
        }else{
            error(getString(R.string.error_update))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_delete -> deleteTask(args.selectedTask)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteTask(task: Task){
        taskViewModel.deleteTask(task)
        Toast.makeText(context, getString(R.string.task_deleted), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_editTaskBottomSheetFragment_to_taskListFragment)
    }

}