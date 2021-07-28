package com.rguzmanc.nuwe.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rguzmanc.nuwe.R
import com.rguzmanc.nuwe.databinding.FragmentLoginBinding
import com.rguzmanc.nuwe.ktx.navigateTo
import com.rguzmanc.nuwe.ktx.showToastMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding){
        btnLogin.setOnClickListener {
            val username = binding.tietUsername.text
            val password = binding.tietPassword.text
            loginViewModel.login(username, password)
        }

        txtSignUp.setOnClickListener {
            navigateTo(LoginNextScreen.SIGN_UP.resource)
        }
    }
    
    private fun initViewModel() = with(loginViewModel){
        uiState.observe(viewLifecycleOwner, { loginState -> updateState(loginState) })
    }

    private fun updateState(loginState: LoginState){
        when(loginState){
            is LoginState.Success -> {

            }

            is LoginState.Loading -> {

            }

            is LoginState.Error -> {
                showToastMessage(loginState.message)
            }

            is LoginState.NextScreen ->{
                navigateTo(loginState.screen.resource)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}