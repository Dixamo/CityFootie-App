package com.example.cityfootie_compose.ui.screens.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.navigation.AppScreens
import com.example.cityfootie_compose.usecases.login.GetLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLogin: GetLogin
) : ViewModel() {

    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled
    val isLoading: Boolean = false

    // Loading Circular ProgressBar
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onEmailChange(value: String) {
        email = value
        _isButtonEnabled.value = isValidEmail(value) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6
    fun onPasswordChange(value: String) {
        password = value
        _isButtonEnabled.value = isValidEmail(email) && isValidPassword(value)
    }

    private val _player: MutableLiveData<Player> = MutableLiveData()
    val player: LiveData<Player> = _player
    private val _isError: MutableLiveData<Boolean> = MutableLiveData()
    val isError: LiveData<Boolean> = _isError
    fun getUser() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (isValidEmail(email) && isValidPassword(password)) {
                val response = getLogin.login(email, password)
                _loading.postValue(false)
                if (response != null) {
                    if (response.isSuccessful) {
                        _player.postValue(response.body())
                    } else {
                        _isError.postValue(true)
                        _player.postValue(null)
                    }
                }
                Log.d("UserViewModel", player.toString())
            }
        }
    }
}