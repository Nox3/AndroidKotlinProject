package com.example.td2.network

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.td2.SHARED_PREF_TOKEN_KEY
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class /* object*/ Api(private val context: Context)
{

    companion object{

        private const val  BASE_URL = "https://android-tasks-api.herokuapp.com/api/"

        /*private const val TOKEN = ""*//*eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo3NSwiZXhwIjoxNjA4NDczMjUxfQ.bN2mQ2VWvqeQoLmNFUc0Dm1xf7y_izbLYUdLGVhtF6Q"
        //"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjozMSwiZXhwIjoxNjA3NTIyOTA3fQ.tYaDnTB44QE58cavFQ5eBrz4Xv5ztx-6LrHkUpdR-b4"
       */
        lateinit var INSTANCE: Api
    }

    private val TOKEN =PreferenceManager.getDefaultSharedPreferences(context).getString(
        SHARED_PREF_TOKEN_KEY, "")
    private val moshi = Moshi.Builder().build()
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${token()}")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    fun token() :String {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(
            SHARED_PREF_TOKEN_KEY, "")?: ""
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val userService: UserService by lazy { retrofit.create(UserService::class.java)}
    val taskService: TaskService by lazy { retrofit.create(TaskService::class.java)}


}

