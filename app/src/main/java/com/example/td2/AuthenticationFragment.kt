package com.example.td2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_authentication.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
*/
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AuthenticationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AuthenticationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthenticationFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_authentication, container, false)
        val signupbutton= view.signup
        val loginbutton=view.login
        signupbutton.setOnClickListener {
            findNavController().navigate(R.id.action_authenticationFragment_to_signupFragment)

        }

        loginbutton.setOnClickListener {
            findNavController().navigate(R.id.action_authenticationFragment_to_loginFragment)

        }

        return view
    }

}
