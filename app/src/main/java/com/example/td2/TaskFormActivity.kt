package com.example.td2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)
        val button = findViewById<Button>(R.id.back)
        button.setOnClickListener{
            val createActivityIntent : Intent = Intent(this, MainActivity::class.java)
            val title = findViewById<TextView>(R.id.title).text.toString()
            val description = findViewById<TextView>(R.id.description).text.toString()
            TaskViewModel.tasks.add( Task("id_$title", title, description))
            startActivity(createActivityIntent)
        }
    }
}
