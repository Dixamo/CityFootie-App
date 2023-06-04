package com.example.cityfootie_compose.ui.screens.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.usecases.get_match.GetFootballMatchUsecases
import com.example.cityfootie_compose.usecases.login.GetPlayerUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getFootballMatchUsecases: GetFootballMatchUsecases
): ViewModel() {
    var response: Response<FootballMatch>? by mutableStateOf(null)
    var footballMatch: FootballMatch? = null
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isSuccessful: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)
    var markerLatitude: Double? by mutableStateOf(null)
    var markerLongitude: Double? by mutableStateOf(null)
    init {
        footballMatch = null
    }
    fun getFootballMatch(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            markerLatitude = latitude
            markerLongitude = longitude
            response = getFootballMatchUsecases.getFootballMatch(latitude, longitude)
            if (response != null) {
                if (response!!.isSuccessful) {
                    isCompleted = true
                    isSuccessful = true
                    footballMatch = response!!.body()
                } else {
                    isCompleted = true
                    isError = true
                    footballMatch = null
                }
            }
            isLoading = false
        }
    }

    fun resetValues() {
        isCompleted = false
        isSuccessful = false
        isError = false
    }
}