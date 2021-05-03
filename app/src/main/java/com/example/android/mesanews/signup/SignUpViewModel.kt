package com.example.android.mesanews.signup

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mesanews.data.modal.LoginResponse
import com.example.android.mesanews.data.modal.SignUpRequest
import com.example.android.mesanews.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val signUpResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    fun signUp(signUpRequest: SignUpRequest, context: Context) {
        viewModelScope.launch {
            signUpResponse.value = repository.startSignUpRequest(signUpRequest, context)
        }
    }
}