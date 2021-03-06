package com.example.td2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.td2.TaskViewModel.tasks
import com.example.td2.network.Api
import com.example.td2.network.TaskRepository
import kotlinx.android.synthetic.main.task_fragment.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class  TaskFragment : Fragment()
{
    private val coroutineScope = MainScope()
    private val tasksRepository = TaskRepository()
    private val tasks = mutableListOf<Task>()
    private val taskAdapter = TaskAdapter(tasks)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)
        view.task_view.adapter = taskAdapter
        view.task_view.layoutManager = LinearLayoutManager(context)


        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // subscribe the fragment to task update
        tasksRepository.getTasks().observe(this, Observer {
            if( it != null){
                tasks.clear()
                tasks.addAll(it)
                taskAdapter.notifyDataSetChanged()

            }
        })
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        coroutineScope.launch {
            Api.INSTANCE.userService.getInfo()
        }
        super.onResume()
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}