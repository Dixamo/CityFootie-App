package com.example.cityfootie_compose.ui.screens.modify

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.usecases.login.GetPlayerUsecases
import com.example.cityfootie_compose.usecases.modify.UpdatePlayerUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val updatePlayerUsecases: UpdatePlayerUsecases,
    private val getPlayerUsecases: GetPlayerUsecases
) : ViewModel() {
    var player: Player? = null
    var name: String by mutableStateOf("")
    var surnames: String by mutableStateOf("")
    var username: String by mutableStateOf("")
    var number: String by mutableStateOf("")

    private fun isValidName(name: String): Boolean = name.length > 1
    fun onNameChange(value: String) {
        name = value.filter { it.isLetter() }
        _isButtonEnabled.value =
            isValidName(value) && isValidSurnames(surnames) && isValidUsername(username) && isValidNumber(
                number
            )
    }


    private fun isValidSurnames(surnames: String): Boolean = surnames.length > 1
    fun onSurnameChange(value: String) {
        surnames = value.filter { it.isLetter() }
        _isButtonEnabled.value =
            isValidName(name) && isValidSurnames(value) && isValidUsername(username) && isValidNumber(
                number
            )
    }

    private fun isValidUsername(username: String): Boolean = username.length > 2
    fun onUsernameChange(value: String) {
        username = value
        _isButtonEnabled.value =
            isValidName(name) && isValidSurnames(surnames) && isValidUsername(value) && isValidNumber(
                number
            )
    }


    private fun isValidNumber(number: String): Boolean = number.length in 1..2
    fun onNumberChange(value: String) {
        number = value.filter { it.isDigit() }
        _isButtonEnabled.value =
            isValidName(name) && isValidSurnames(surnames) && isValidUsername(username) && isValidNumber(
                value
            )
    }

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled


    var response: Response<Void>? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)
    var isModifyCompleted: Boolean by mutableStateOf(false)

    fun getPlayer(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = getPlayerUsecases.getPlayerByEmail(
                email
            )
            if (response != null) {
                if (response.isSuccessful) {
                    isCompleted = true
                    player = response.body()
                    name = player!!.name!!
                    surnames = player!!.surnames!!
                    username = player!!.username!!
                    number = player!!.number!!.toString()
                } else {
                    isError = true
                }
            }
            isLoading = false
        }
    }

    fun updatePlayer(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            response = updatePlayerUsecases.updatePlayer(
                email,
                name,
                surnames,
                username,
                number.toInt()
            )
            if (response != null) {
                if (response!!.isSuccessful) {
                    isModifyCompleted = true
                    response!!.body()
                } else {
                    isError = true
                }
            }
            isLoading = false
        }
    }
}