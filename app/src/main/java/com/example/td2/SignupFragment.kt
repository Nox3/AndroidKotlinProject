package com.example.td2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_signup.view.*

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
        buttonsignup.setOnClickListener {
            if(!email.text.toString().isEmpty() && !password.text.toString().isEmpty() && !password_conf.text.toString().isEmpty() ){

            }
            else {
                Toast.makeText(context, "Eléments essentiels non remplis", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }


}
