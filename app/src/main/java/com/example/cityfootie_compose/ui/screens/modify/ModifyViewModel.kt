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
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val updatePlayerUsecases: UpdatePlayerUsecases,
    private val getPlayerUsecases: GetPlayerUsecases
): ViewModel() {
    var name: String by mutableStateOf("")
    var surnames: String by mutableStateOf("")
    var number: String by mutableStateOf("")

    private fun isValidName(name: String): Boolean = name.length > 1
    fun onNameChange(value: String) {
        name = value
        _isButtonEnabled.value = isValidName(value) && isValidSurnames(surnames) && isValidNumber(number)
    }


    private fun isValidSurnames(surnames: String): Boolean = surnames.length > 1
    fun onSurnameChange(value: String) {
        surnames = value
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(value) && isValidNumber(number)
    }


    private fun isValidNumber(number: String): Boolean = number.length < 3
    fun onNumberChange(value: String) {
        number = value
        _isButtonEnabled.value = isValidName(name) && isValidSurnames(surnames) && isValidNumber(value)
    }

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled


    var player: Player? = null
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)

    /*init {
        getPlayer()
    }*/
    fun getPlayer(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = getPlayerUsecases.getPlayer(
                email,
                password
            )
            if (response != null) {
                if (response.isSuccessful) {
                    isCompleted = true
                    player = response.body()
                } else {
                    isError = true
                }
            }
            isLoading = false
        }
    }

    fun updatePlayer(email: String){
        viewModelScope.launch (Dispatchers.IO) {
            val response = updatePlayerUsecases.updatePlayer(
                email,
                name,
                surnames,
                number.toInt()
            )
            if (response != null) {
                if (response.isSuccessful) {
                    isCompleted = true
                } else {
                    isError = true
                }
            }
        }
    }
}