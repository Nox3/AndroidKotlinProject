package com.example.td2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.td2.network.Api
import kotlinx.android.synthetic.main.header_fragment.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HeaderFragment : Fragment()
{
    private val coroutineScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.header_fragment, container)
    }

    override fun onResume() {
        super.onResume()
        Glide.with(this).load("https://goo.gl/gEgYUd").apply(RequestOptions.circleCropTransform()).into(imageView)
        coroutineScope.launch {Api.INSTANCE.userService.getInfo()}
        coroutineScope.launch {Api.INSTANCE.taskService.getTasks()}
        val text=user_name
        coroutineScope.launch{
            val response = Api.INSTANCE.userService.getInfo()

            if(response.isSuccessful) {
                val userName = response.body()?.firstname + " " + response.body()?.lastname
                text.text = userName
            }

        }

        val avatar= imageView
        avatar.isClickable=true
        avatar.setOnClickListener {
            val newAvatarIntent= Intent(context, UserInfoActivity::class.java)
            startActivity(newAvatarIntent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}