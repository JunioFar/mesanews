package com.example.android.mesanews.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.mesanews.R
import com.example.android.mesanews.data.modal.SignUpRequest
import com.example.android.mesanews.databinding.SignUpFragmentBinding
import com.example.android.mesanews.utils.closeKeyBoard
import com.example.android.mesanews.utils.verifyEditTextIsNotEmpty
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()

    private lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)

        viewModel.signUpResponse.observe(viewLifecycleOwner, {
            binding.isLoading = false
            if (it.isSuccessful){
                val direction = SignUpFragmentDirections.actionSignUpFragmentToNewsFeedFragment()
                findNavController().navigate(direction)
            } else {
                Snackbar.make(binding.root, it.message(), Snackbar.LENGTH_SHORT).show()
            }
        })

        setClickListeners()

        return binding.root
    }

    private fun setClickListeners() {
        binding.sigUpButton.setOnClickListener {
            if(allInfoIsOk()){
                closeKeyBoard(binding.root)
                binding.isLoading = true
                viewModel.signUp(getSignUpRequest(), requireContext())
            }
        }
    }

    private fun getSignUpRequest(): SignUpRequest {
        return SignUpRequest(
                binding.userNameEditText.text.toString(),
                binding.userEmailEditText.text.toString(),
                binding.userPassword.text.toString()
        )
    }

    private fun allInfoIsOk(): Boolean {
        if (!verifyEditTextIsNotEmpty(
                        binding.userNameEditText,
                        binding.userEmailEditText,
                        binding.userPassword,
                        binding.userPasswordConfirmation)
        ) {
            return false
        } else if (!isPasswordTheSame()) {
            return false
        }
        return true
    }

    private fun isPasswordTheSame(): Boolean {
        val result = binding.userPassword.text.toString() == binding.userPasswordConfirmation.text.toString()
        if (!result) {
            binding.userPassword.requestFocus()
            binding.userPassword.error = getString(R.string.different_password_error)
            return false
        }
        return true
    }

}