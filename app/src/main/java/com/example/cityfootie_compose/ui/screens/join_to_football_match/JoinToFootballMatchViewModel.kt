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
import com.example.cityfootie_compose.usecases.login.GetPlayerUsecases
import com.example.cityfootie_compose.usecases.put_match.PutFootballMatchUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JoinToFootballMatchViewModel @Inject constructor(
    private val putFootballMatchUsecases: PutFootballMatchUsecases,
): ViewModel(){
    var Email: String by mutableStateOf("")
    var markerLatitude: Double? by mutableStateOf(null)
    var markerLongitude: Double? by mutableStateOf(null)

    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    var player: Player? = null
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)


    fun putFootballMatch(email: String, latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            Email = email
            markerLatitude = latitude
            markerLongitude = longitude
            val response = putFootballMatchUsecases.putFootballMatch(email, latitude, longitude)
            if (response != null) {
                if (response.isSuccessful) {
                    isCompleted = true
                } else {
                    isError = true
                }
            }
            isLoading = false
        }
    }
}