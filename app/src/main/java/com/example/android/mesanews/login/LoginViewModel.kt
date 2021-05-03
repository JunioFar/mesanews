package com.example.android.mesanews.login

import android.content.Context
import androidx.lifecycle.*
import com.example.android.mesanews.data.repository.UserRepository
import com.example.android.mesanews.data.modal.LoginRequest
import com.example.android.mesanews.data.modal.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    fun callLogin(loginRequest: LoginRequest, context: Context) {
        viewModelScope.launch {
            loginResponse.value = repository.startLoginRequest(loginRequest, context)
        }
    }
}