package com.example.td2
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.preference.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(this)
        val title= sharedpreferences.getString("title", "")

        val colorBar=sharedpreferences.getString("ColorBar", "")
        val mybar= supportActionBar
        mybar?.setTitle(title)
        mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorBar)))

    }

    class SettingsFragment : PreferenceFragmentCompat() {


        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences , rootKey)


        }
        override fun onDisplayPreferenceDialog(preference: Preference?) {

            val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context)
           // val sharedpreferences= context?.getSharedPreferences("userpref", Context.MODE_PRIVATE)
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

            super.onDisplayPreferenceDialog(preference)
        }








    }


}
