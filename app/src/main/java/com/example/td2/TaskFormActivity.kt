package com.example.td2

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.td2.network.Api
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class TaskFormActivity : AppCompatActivity() {

    private val coroutineScope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)
        val button = findViewById<Button>(R.id.back)
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val colortext=sharedpreferences.getString("PoliceColor", "")
        val title= sharedpreferences.getString("title", "")
        val colorBar=sharedpreferences.getString("ColorBar", "")
        val textTitle=findViewById<EditText>(R.id.title)
        val textdescription= findViewById<EditText>(R.id.description)
        textTitle.setTextColor(Color.parseColor(colortext))
        textdescription.setTextColor(Color.parseColor(colortext))
        val mybar= supportActionBar
        mybar?.setTitle(title)
        mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))


        val intent= intent
        if(intent.hasExtra("isEdit")){

            textTitle.setText(intent.getStringExtra("title"))
            textdescription.setText(intent.getStringExtra("description"))

        }

        button.setOnClickListener{
            val createActivityIntent : Intent = Intent(this, MainActivity::class.java)
            val title = findViewById<TextView>(R.id.title).text.toString()
            val description = findViewById<TextView>(R.id.description).text.toString()
            coroutineScope.launch{
                val response = Api.INSTANCE.taskService.createTask( Task("id_$title", title, description))
                if(response.isSuccessful){
                    TaskViewModel.tasks.add( Task("id_$title", title, description))
                 }
                }


            startActivity(createActivityIntent)
        }
    }
}
