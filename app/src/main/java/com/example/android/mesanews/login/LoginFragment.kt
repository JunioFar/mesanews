package com.example.android.mesanews.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.mesanews.data.modal.LoginRequest
import com.example.android.mesanews.databinding.LoginFragmentBinding
import com.example.android.mesanews.utils.closeKeyBoard
import com.example.android.mesanews.utils.verifyEditTextIsNotEmpty
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        setClickListeners()
        setObservers()

        if (context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.contains("TOKEN") == true){
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNewsFeedFragment())
        }

        return binding.root
    }

    private fun setObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.isLoading = false
            if (it.isSuccessful){
                val direction = LoginFragmentDirections.actionLoginFragmentToNewsFeedFragment()
                findNavController().navigate(direction)
            } else {
                Snackbar.make(binding.root, it.message(), Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun setClickListeners() {
        binding.loginButton.setOnClickListener {
            if(verifyEditTextIsNotEmpty(
                            binding.editTextEmail,
                            binding.editTextPassword)
            ) {
                closeKeyBoard(binding.root)
                binding.isLoading = true
                viewModel.callLogin(getLoginInfo(), requireContext())
            }
        }

        binding.newAccountText.setOnClickListener{
            val direction = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(direction)
        }
    }

    private fun getLoginInfo(): LoginRequest {
        return LoginRequest(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString())
    }

}