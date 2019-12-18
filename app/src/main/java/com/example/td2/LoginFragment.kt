    package com.example.td2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
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
        buttonlogin.setOnClickListener {
            if(!email.text.toString().isEmpty() && !password.text.toString().isEmpty()){

                val loginform= LoginForm(email.text.toString(), password.text.toString())
                coroutineScope.launch {
                   val response= Api.userService.login(loginform)
                    if(response.isSuccessful){
                        val token=response.body()?.token
                        TokenToPreference(token)// Tester si le token est null car s'il est nul on doit envoyer un message d'erreur à l'utilisateur (cf toast juste en bas)

                    }
                }


            }
            else {
                Toast.makeText(context, "text", Toast.LENGTH_LONG).show()
            }
    }
    return view
    }


    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    fun TokenToPreference(token: String){

            context?.getSharedPreferences("Contraintes", Context.MODE_PRIVATE)?.edit {
                putString(SHARED_PREF_TOKEN_KEY, token)
            }
        //PreferenceManager.getDefaultSharedPreferences(context).edit {

    }
}
