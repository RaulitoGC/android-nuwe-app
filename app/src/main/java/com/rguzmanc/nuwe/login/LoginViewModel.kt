package com.rguzmanc.nuwe.login

import android.text.Editable
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rguzmanc.nuwe.R
import com.rguzmanc.nuwe.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

enum class LoginNextScreen (@IdRes val resource: Int){
    FORGOT_PASSWORD(R.id.action_loginFragment_to_signUpFragment), SIGN_UP((R.id.action_loginFragment_to_signUpFragment))
}

sealed class LoginState{
    object Success : LoginState()
    data class NextScreen(val screen: LoginNextScreen): LoginState()
    data class Error(val message: String) : LoginState()
    data class Loading(val showLoading: Boolean): LoginState()
}
class LoginViewModel(private val loginUseCase: LoginUseCase): ViewModel() {

    private val _uiState: MutableLiveData<LoginState> = MutableLiveData()
    val uiState: LiveData<LoginState> = _uiState

    fun login(username: Editable?, password: Editable?) = viewModelScope.launch{
        showLoading(showLoading = true)
        if(!username.isNullOrEmpty() && !password.isNullOrEmpty()){
            loginUseCase(username = username.toString(), password = password.toString())
                .catch { throwable ->
                    showLoading(showLoading = false)
                    Timber.e(throwable)
                    showError(throwable.message.orEmpty())
                }.collect {
                    showLoading(showLoading = false)
                    showSuccess()
                }
        }else{
            showError("Invalid Credentials")
        }
    }

    fun loginWithSocialNetwork(socialNetwork: SocialNetwork){

    }

    private fun showLoading(showLoading: Boolean){
        _uiState.value = LoginState.Loading(showLoading)
    }

    private fun showError(message: String){
        _uiState.value = LoginState.Error(message)
    }

    private fun showSuccess(){
        _uiState.value = LoginState.Success
    }

    enum class SocialNetwork{
        GOOGLE, GITHUB, DISCORD
    }
}