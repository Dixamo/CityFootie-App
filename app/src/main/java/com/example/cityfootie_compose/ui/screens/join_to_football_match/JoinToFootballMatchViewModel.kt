package com.example.cityfootie_compose.ui.screens.join_to_football_match

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.usecases.get_match.GetFootballMatchUsecases
import com.example.cityfootie_compose.usecases.get_players_by_match.GetPlayersByFootballMatchUsecases
import com.example.cityfootie_compose.usecases.put_match.PutFootballMatchUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JoinToFootballMatchViewModel @Inject constructor(
    private val getPlayersByFootballMatchUsecases: GetPlayersByFootballMatchUsecases,
    private val getFootballMatchUsecases: GetFootballMatchUsecases,
    private val putFootballMatchUsecases: PutFootballMatchUsecases
) : ViewModel() {
    var response: Response<Void>? by mutableStateOf(null)
    var footballMatch: FootballMatch? = null
    var email: String by mutableStateOf("")
    private var markerLatitude: Double? by mutableStateOf(null)
    private var markerLongitude: Double? by mutableStateOf(null)

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    var players: Set<Player>? = null
    var isLoading: Boolean by mutableStateOf(false)
    var isGetPlayersCompleted: Boolean by mutableStateOf(false)
    var isGetCompleted: Boolean by mutableStateOf(false)
    var isPostCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)

    /**
     * Método encargado de obtener los jugadores inscritos en un partido a través de la longitud y de la latitud.
     */
    fun getPlayersByFootballMatch(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            markerLatitude = latitude
            markerLongitude = longitude
            val response =
                getPlayersByFootballMatchUsecases.getPlayersByFootballMatch(latitude, longitude)
            if (response != null) {
                if (response.isSuccessful) {
                    isGetPlayersCompleted = true
                    players = response.body()
                } else {
                    isError = true
                    footballMatch = null
                }
            }
            isLoading = false
        }
    }

    /**
     * Método encargado de obtener un partido ya creado a través de la latitud y la longitud.
     */
    fun getFootballMatch(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            markerLatitude = latitude
            markerLongitude = longitude
            val response = getFootballMatchUsecases.getFootballMatch(latitude, longitude)
            if (response != null) {
                if (response.isSuccessful) {
                    isGetCompleted = true
                    footballMatch = response.body()
                } else {
                    isError = true
                    footballMatch = null
                }
            }
            isLoading = false
        }
    }

    /**
     * Método encargado de inscribir a un jugador en cierto partido (en caso de que no esté inscrito ya) a través de la longitud, latitud y email.
     */
    fun putFootballMatch(latitude: Double, longitude: Double, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            this@JoinToFootballMatchViewModel.email = email
            markerLatitude = latitude
            markerLongitude = longitude
            response = putFootballMatchUsecases.putFootballMatch(latitude, longitude, email)
            if (response != null) {
                if (response!!.isSuccessful) {
                    isPostCompleted = true
                } else {
                    isError = true
                }
            }
            isLoading = false
        }
    }
}