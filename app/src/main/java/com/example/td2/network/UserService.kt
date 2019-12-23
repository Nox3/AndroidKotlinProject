package com.example.td2.network

import com.example.td2.LoginForm
import com.example.td2.SignUpForm
import com.example.td2.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("users/info")
    suspend fun getInfo(): Response<UserInfo>

    @POST("users/login")
    suspend fun login(@Body user: LoginForm): Response<TokenResponse>

    @POST("users/signup")
    suspend fun signup(@Body user: SignUpForm): Response<TokenResponse>
}