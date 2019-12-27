package com.example.td2

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.td2.network.Api
import kotlinx.android.synthetic.main.fragment_signup.view.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignupFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {
    // TODO: Rename and change types of parameters
   /* private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null*/
    private val coroutineScope = MainScope()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_signup, container, false)
        val buttonsignup=view.signup
        val firstname=view.firstname
        val lastname=view.lastname
        val email=view.email
        val password=view.password
        val password_conf=view.confirmpassword
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context)
        val colortext=sharedpreferences.getString("PoliceColor", "")
        email.setTextColor(Color.parseColor(colortext))
        password.setTextColor(Color.parseColor(colortext))
        firstname.setTextColor(Color.parseColor(colortext))
        lastname.setTextColor(Color.parseColor(colortext))
        password_conf.setTextColor(Color.parseColor(colortext))

        buttonsignup.setOnClickListener {
            if(!email.text.toString().isEmpty() && !password.text.toString().isEmpty() && !password_conf.text.toString().isEmpty() ){
                val signupform= SignUpForm(firstname.text.toString(), lastname.text.toString(), email.text.toString(), password.text.toString(), password_conf.text.toString())
                coroutineScope.launch {
                    val response = Api.INSTANCE.userService.signup(signupform)
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        if (token != null) {
                            TokenToPreference(token)
                            val intent = Intent(context, MainActivity::class.java)
                            //afficher les tâches
                            startActivity(intent)

                        } else Toast.makeText(context, "token null", Toast.LENGTH_LONG).show()
                    }
                    Toast.makeText(context, "Pb request", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(context, "Eléments essentiels non remplis", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }


    fun TokenToPreference(token: String){

        context?.getSharedPreferences("Token", Context.MODE_PRIVATE)?.edit {
            putString(SHARED_PREF_TOKEN_KEY, token)
        }
        //PreferenceManager.getDefaultSharedPreferences(context).edit {

    }




}
