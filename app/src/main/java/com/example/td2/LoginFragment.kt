    package com.example.td2

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.td2.network.Api
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val coroutineScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_login, container, false)
        val buttonlogin=view.login
        val email=view.mail
        val password= view.password
        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context)
        val colortext=sharedpreferences.getString("PoliceColor", "")
        email.setTextColor(Color.parseColor(colortext))
        password.setTextColor(Color.parseColor(colortext))


        buttonlogin.setOnClickListener {
            if(!email.text.toString().isEmpty() && !password.text.toString().isEmpty()){

                val loginform= LoginForm(email.text.toString(), password.text.toString())
                coroutineScope.launch {
                   val response= Api.INSTANCE.userService.login(loginform)
                    if(response.isSuccessful){
                        val token=response.body()?.token
                        if(token!=null) {
                            TokenToPreference(token)
                            val intent = Intent(context, MainActivity::class.java)
                            //afficher les tâches
                            startActivity(intent)

                        }
                        else Toast.makeText(context, "token null", Toast.LENGTH_LONG).show()
                    }

                }


            }
            else {
                Toast.makeText(context, "Formulaire non rempli", Toast.LENGTH_LONG).show()
            }
    }
    return view
    }


    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    fun TokenToPreference(token: String){

            context?.getSharedPreferences("Token", Context.MODE_PRIVATE)?.edit {
                putString(SHARED_PREF_TOKEN_KEY, token)
            }
        //PreferenceManager.getDefaultSharedPreferences(context).edit {

    }

}
