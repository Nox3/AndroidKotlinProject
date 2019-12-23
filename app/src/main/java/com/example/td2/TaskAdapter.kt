package com.example.td2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*


class TaskAdapter(private val tasks : MutableList<Task>) : RecyclerView.Adapter<TaskViewHolder>()
{
   private lateinit var truc : ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        truc=parent
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
        holder.itemView.button_delete.setOnClickListener { onDeleteClickListener(tasks[position]) }
        holder.itemView.edit.setOnClickListener { onEditClickListener(tasks[position], truc ) }
    }

    private fun onDeleteClickListener(task : Task) {
        tasks.remove(task)
        notifyDataSetChanged()
    }

    private fun onEditClickListener(task: Task, parent: ViewGroup)
    {
        val intent = Intent(parent.context , TaskFormActivity::class.java)
        intent.putExtra("title", task.title)
        intent.putExtra("isEdit", "edit")
        intent.putExtra("description", task.description)
        tasks.remove(task)

        startActivity(parent.context, intent, null)
    }

}