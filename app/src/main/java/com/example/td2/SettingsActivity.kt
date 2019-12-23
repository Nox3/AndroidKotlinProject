package com.example.td2
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
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
    }

    class SettingsFragment : PreferenceFragmentCompat() {


        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)


        }
        override fun onDisplayPreferenceDialog(preference: Preference?) {
            val appBarTitlePreference: EditTextPreference? = findPreference("AppBarTitle")
            val mybar = (activity as AppCompatActivity).supportActionBar
            mybar?.setTitle(appBarTitlePreference?.text)
            val appBarColorPreference: ListPreference? = findPreference("AppBarColor")
            val appStyleColorPreference : ListPreference?= findPreference("PolicePreference")
            val colorchosen = appBarColorPreference?.value
                if(colorchosen!=null){
                    mybar?.setBackgroundDrawable( ColorDrawable(Color.parseColor(colorchosen)))


                }
            /*if (colorchosen != null) {
                mybar?.setBackgroundDrawable(getDrawable(activity as AppCompatActivity, colorchosen.toInt()))
            }

            mybar?.setBackgroundDrawable(new ColorDrawable("AppBarColor"));
            mybar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this!!.context!!,
              appBarColorPreference?.value?.toColorInt()!!
            )))*/
           /*val colorpolice= appStyleColorPreference?.value
                if (colorpolice!=null){

                }*/

            super.onDisplayPreferenceDialog(preference)

            super.onDisplayPreferenceDialog(preference)
        }








    }


}
