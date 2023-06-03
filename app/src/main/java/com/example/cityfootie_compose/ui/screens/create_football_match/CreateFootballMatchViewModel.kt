package com.example.cityfootie_compose.ui.screens.create_football_match

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateFootballMatchViewModel @Inject constructor(
): ViewModel() {
    var date: String by mutableStateOf("")
    var numberMax: String by mutableStateOf("")
    var numberPlayers: String by mutableStateOf("")
    var street: String by mutableStateOf("")

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private fun isValidDate(date: String): Boolean = date.length > 1
    fun onDateChange(value: String) {
        date = value
        _isButtonEnabled.value = isValidDate(value) && isValidNumberMax(numberMax) && isValidNumberPlayers(numberPlayers) && isValidStreet(street)
    }

    private fun isValidNumberMax(numberMax: String): Boolean = numberMax.length > 1
    fun onNumberMaxChange(value: String) {
        numberMax = value
        _isButtonEnabled.value = isValidDate(date) && isValidNumberMax(value) && isValidNumberPlayers(numberPlayers) && isValidStreet(street)
    }

    private fun isValidNumberPlayers(numberPlayers: String): Boolean = numberPlayers.length > 1
    fun onNumberPlayersChange(value: String) {
        numberPlayers = value
        _isButtonEnabled.value = isValidDate(date) && isValidNumberMax(numberMax) && isValidNumberPlayers(value) && isValidStreet(street)
    }

    private fun isValidStreet(street: String): Boolean = street.length > 1
    fun onStreetChange(value: String) {
        street = value
        _isButtonEnabled.value = isValidDate(date) && isValidNumberMax(numberMax) && isValidNumberPlayers(numberPlayers) && isValidStreet(value)
    }
}

