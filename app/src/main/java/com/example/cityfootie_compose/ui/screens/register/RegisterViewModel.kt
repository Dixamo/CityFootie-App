package com.example.cityfootie_compose.ui.screens.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.usecases.post_player.PostPlayerUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val postPlayerUsecases: PostPlayerUsecases
) : ViewModel() {
    var name: String by mutableStateOf("")
    var surnames: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var number: String by mutableStateOf("")
    var username: String by mutableStateOf("")
    var password: String by mutableStateOf("")

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    var response: Response<Void>? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)

    private fun isValidName(name: String): Boolean = name.length > 1
    fun onNameChange(value: String) {
        name = value
        _isButtonEnabled.value = isValidName(value) && isValidSurnames(surnames) && isValidEmail(email) && isValidNumber(number) && isValidUsername(username) && isValidPassword(password)
    }

    private fun isValidSurnames(surnames: String): Boolean = surnames.length > 1
    fun onSurnamesChange(value: String) {
        surnames = value
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(value) && isValidEmail(email) && isValidNumber(number) && isValidUsername(username) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onEmailChange(value: String) {
        email = value
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(surnames) && isValidEmail(value) && isValidNumber(number) && isValidUsername(username) && isValidPassword(password)
    }

    private fun isValidNumber(number: String): Boolean = number.length in 1..2
    fun onNumberChange(value: String) {
        number = value.filter { it.isDigit() }
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(surnames) && isValidEmail(email) && isValidNumber(value) && isValidUsername(username) && isValidPassword(password)
    }

    private fun isValidUsername(username: String): Boolean = username.length > 2
    fun onUsernameChange(value: String) {
        username = value
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(surnames) && isValidEmail(email) && isValidNumber(number) && isValidUsername(value) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 3
    fun onPasswordChange(value: String) {
        password = value
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(surnames) && isValidEmail(email) && isValidNumber(number) && isValidUsername(username) && isValidPassword(value)
    }

    fun postUser() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val newPlayer = Player(name, surnames, username, email, password, number.toInt())
            response = postPlayerUsecases.postPlayer(newPlayer)
            if (response != null) {
                if (response!!.isSuccessful) {
                    isCompleted = true
                    response!!.body()
                } else {
                    isError = true
                }
            }
        }
    }
}