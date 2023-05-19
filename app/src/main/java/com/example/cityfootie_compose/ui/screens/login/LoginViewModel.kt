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
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.usecases.login.GetPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getPlayer: GetPlayer
) : ViewModel() {

    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    // Loading Circular ProgressBar
    /*private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading*/

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun onEmailChange(value: String) {
        email = value
        _isButtonEnabled.value = isValidEmail(value) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 4
    fun onPasswordChange(value: String) {
        password = value
        _isButtonEnabled.value = isValidEmail(email) && isValidPassword(value)
    }

    var player: Player? = null
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)
    fun getPlayer(): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = getPlayer.getPlayer(email, password)
            if (response != null) {
                if (response.isSuccessful) {
                    isCompleted = true
                    player = response.body()
                } else {
                    isError = true
                }
            }
            delay(700)
            isLoading = false
        }
        return isCompleted
    }
}