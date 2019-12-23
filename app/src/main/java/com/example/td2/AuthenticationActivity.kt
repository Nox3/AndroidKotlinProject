package com.example.td2

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_authentication)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

       val colorBar=sharedpreferences.getString("ColorBar", "")
       // val mybar =(activity as AppCompatActivity).supportActionBar
        val mybar= supportActionBar
        if(title!=null){
        mybar?.setTitle(title)}
        if(colorBar!= null){
        mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))}


    }
    override fun onRestart() {
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

        val colorBar=sharedpreferences.getString("ColorBar", "")
        // val mybar =(activity as AppCompatActivity).supportActionBar
        val mybar= supportActionBar
        if(title!=null){
            mybar?.setTitle(title)}
        if(colorBar!= null){
            mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))}
        super.onRestart()
    }
}
