package com.example.cityfootie_compose.ui.screens

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@HiltViewModel
class LoginViewModel : BaseViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _enableButton = MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> = _enableButton
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _enableButton.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }
}