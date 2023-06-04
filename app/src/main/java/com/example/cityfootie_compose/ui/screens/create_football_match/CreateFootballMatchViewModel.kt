package com.example.cityfootie_compose.ui.screens.create_football_match

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.usecases.post_match.PostFootballMatchUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class CreateFootballMatchViewModel @Inject constructor(
    private val postFootballMatchUsecases: PostFootballMatchUsecases
): ViewModel() {
    var dateString: String by mutableStateOf("")
    var numberMax: String by mutableStateOf("")
    var numberPlayers: String by mutableStateOf("")

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private fun parseDate(dateString: String): Timestamp {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return Timestamp(date.time)
    }

    private fun formatDateString(timestamp: Timestamp): String {
        val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        outputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val formattedDate = outputFormat.format(timestamp)
        return formattedDate.replace("'", "").replace("Z", "").replace("T", " ")
    }

    private fun isValidDate(date: String): Boolean = date.length > 0
    fun onDateChange(value: String) {
        dateString = value
        _isButtonEnabled.value = isValidDate(value) && isValidNumberMax(numberMax) && isValidNumberPlayers(numberPlayers)
    }

    private fun isValidNumberMax(numberMax: String): Boolean = numberMax.length > 0
    fun onNumberMaxChange(value: String) {
        numberMax = value
        _isButtonEnabled.value = isValidDate(dateString) && isValidNumberMax(value) && isValidNumberPlayers(numberPlayers)
    }

    private fun isValidNumberPlayers(numberPlayers: String): Boolean = numberPlayers.length > 0
    fun onNumberPlayersChange(value: String) {
        numberPlayers = value
        _isButtonEnabled.value = isValidDate(dateString) && isValidNumberMax(numberMax) && isValidNumberPlayers(value)
    }

    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)

    fun postFootballMatch(latitude : String, longitude : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val timestamp = parseDate(dateString)
            val formattedDate = formatDateString(timestamp)
            val newFootballMatch: FootballMatch = FootballMatch(
                latitude.toDouble(),
                longitude.toDouble(),
                numberMax.toInt(),
                numberPlayers.toInt(),
                Timestamp.valueOf(formattedDate)
            )
            val response = postFootballMatchUsecases.postFootballMatch(newFootballMatch)
            if (response != null) {
                if (response.isSuccessful) {
                    isCompleted = true
                    response.body()
                } else {
                    isError = true
                }
            }
        }
    }
}

