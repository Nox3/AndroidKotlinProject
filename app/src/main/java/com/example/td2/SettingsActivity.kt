package com.example.td2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val colorchosen = appBarColorPreference?.value
            //if (colorchosen != null) {
               // mybar?.setBackgroundDrawable(getDrawable(activity as AppCompatActivity, colorchosen.toInt()))
            //}

            //mybar?.setBackgroundDrawable(new ColorDrawable("AppBarColor"));
            //mybar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this!!.context!!,
            //  appBarColorPreference?.value?.toColorInt()!!
            //)))

            super.onDisplayPreferenceDialog(preference)

            super.onDisplayPreferenceDialog(preference)
        }








    }


}
