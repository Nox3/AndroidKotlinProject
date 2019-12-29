package com.example.td2

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.td2.network.Api
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskFormIntent = Intent(this, TaskFormActivity::class.java)
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

        val colorBar=sharedpreferences.getString("ColorBar", "")

        val mybar= supportActionBar
        if(title!=""){
            mybar?.setTitle(title)}
        if(colorBar!= ""){
            mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))}
        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            startActivity(taskFormIntent)
        }
    }

    override fun onRestart() {
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

        val colorBar=sharedpreferences.getString("ColorBar", "")
        // val mybar =(activity as AppCompatActivity).supportActionBar
        val mybar= supportActionBar
        if(title!=""){
            mybar?.setTitle(title)}
        if(colorBar!= ""){
            mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))}
        super.onRestart()
    }

}
