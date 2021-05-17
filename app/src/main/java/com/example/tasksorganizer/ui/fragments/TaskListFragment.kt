package com.example.tasksorganizer.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasksorganizer.R
import com.example.tasksorganizer.data.models.Task
import com.example.tasksorganizer.ui.fragments.adapter.TaskListAdapter
import com.example.tasksorganizer.ui.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_task_list.*

class TaskListFragment : Fragment(R.layout.fragment_task_list) {
    private lateinit var taskList: List<Task>
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: TaskListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = TaskListAdapter()
        rv_task.adapter = adapter
        rv_task.layoutManager = LinearLayoutManager(context)

       taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskViewModel.getAllTasks.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            taskList = it

            if(it.isEmpty()){
                rv_task.visibility = View.GONE
                tv_emptyViewTitle.visibility = View.VISIBLE
                tv_emptyView.visibility = View.VISIBLE
                iv_emptyView.visibility = View.VISIBLE
            }else{
                rv_task.visibility = View.VISIBLE
                tv_emptyViewTitle.visibility = View.GONE
                tv_emptyView.visibility = View.GONE
                iv_emptyView.visibility = View.GONE
            }
        })

        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(taskList)
            swipeToRefresh.isRefreshing = false
        }

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskBottomSheetFragment)
        }

    }

}