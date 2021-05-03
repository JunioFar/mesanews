package com.example.android.mesanews.data.repository

import android.content.Context
import com.example.android.mesanews.api.MesaNewsService
import com.example.android.mesanews.data.modal.LoginRequest
import com.example.android.mesanews.data.modal.LoginResponse
import com.example.android.mesanews.data.modal.SignUpRequest
import retrofit2.Response
import javax.inject.Inject


class UserRepository @Inject constructor(
        private val service: MesaNewsService) {

    suspend fun startLoginRequest(loginRequest: LoginRequest, context: Context): Response<LoginResponse> {
        val result = service.login(loginRequest)
        saveToken(result.body()?.token, context)
        return result
    }

    suspend fun startSignUpRequest(signUpRequest: SignUpRequest, context: Context): Response<LoginResponse> {
        val result = service.signUp(signUpRequest)
        saveToken(result.body()?.token, context)
        return result
    }

    private fun saveToken(token: String?, context: Context) {
        val sharedPrefs = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.apply{
            putString("TOKEN", token)
        }.apply()
    }


}