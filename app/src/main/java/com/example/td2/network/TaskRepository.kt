package com.example.td2.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.td2.Task
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TaskRepository {
    private val tasksService = Api.taskService
    private val coroutineScope = MainScope()


    fun getTasks(): LiveData<List<Task>?> {
        val tasks = MutableLiveData<List<Task>?>()
        coroutineScope.launch { tasks.postValue( loadTasks()) }
        return tasks
    }

    private suspend fun loadTasks(): List<Task>? {
        val taskReponse = tasksService.getTasks()
        Log.e(
            "loadTask",
            taskReponse.toString() +
            taskReponse.body()
        )
        return if(taskReponse.isSuccessful) taskReponse.body() else null
    }
}