package com.example.td2
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.preference.*
import android.content.SharedPreferences
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()

        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val title = sharedpreferences.getString("title", "")

        val colorBar = sharedpreferences.getString("ColorBar", "")
        val mybar = supportActionBar
        if(title!=""){
        mybar?.setTitle(title)}
        if(colorBar!=""){
        mybar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(colorBar)))}


       /*sharedpreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, s ->
           if(s=="title"){

               val title = sharedPreferences.getString("title", "")
               mybar?.setTitle(title)


           }
           if(s== "ColorBar"){
               val colorBar = sharedPreferences.getString("ColorBar", "")
               mybar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(colorBar)))

           }
       }*/


    }




    class SettingsFragment : PreferenceFragmentCompat() {


        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences , rootKey)
            val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context)
            val mybar = (activity as AppCompatActivity).supportActionBar

            /*sharedpreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, s ->
                if(s=="title"){

                    val title = sharedPreferences.getString("title", "")
                    mybar?.setTitle(title)


                }
                if(s== "ColorBar"){
                    val colorBar = sharedPreferences.getString("ColorBar", "")
                    mybar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(colorBar)))

                }
            }
            */

        }
        override fun onDisplayPreferenceDialog(preference: Preference?) {

            val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context)

            val appBarTitlePreference: EditTextPreference? = findPreference("AppBarTitle")
            val mybar = (activity as AppCompatActivity).supportActionBar
            mybar?.setTitle(appBarTitlePreference?.text)
            sharedpreferences.edit().putString("title", appBarTitlePreference?.text)?.apply()
            val appBarColorPreference: ListPreference? = findPreference("AppBarColor")
            val appStyleColorPreference : ListPreference?= findPreference("PoliceColor")
            val colorchosen = appBarColorPreference?.value
            if(colorchosen!=null){
                mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorchosen)))
                sharedpreferences.edit().putString("ColorBar", colorchosen)?.apply()



            }
            val colorpolice= appStyleColorPreference?.value
                 if (colorpolice!=null){

                     val textfiel1=(activity as AppCompatActivity).findViewById<EditText>(R.id.firstname)
                     textfiel1?.setTextColor(Color.parseColor(colorpolice))
                     sharedpreferences.edit().putString("ColorPolice", colorpolice)?.apply()

                 }


            super.onDisplayPreferenceDialog(preference)



        }









    }



}
