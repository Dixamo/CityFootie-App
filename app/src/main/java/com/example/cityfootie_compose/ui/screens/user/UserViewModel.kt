package com.example.cityfootie_compose.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.usecases.get_player.GetPlayerUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getPlayerUsecases: GetPlayerUsecases
) : ViewModel() {
    private val _isButtonEnabled = MutableLiveData(false)
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    var player: Player? = null
    var isLoading: Boolean by mutableStateOf(false)
    var isCompleted: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)

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
                } else {
                    isError = true
                }
            }
            isLoading = false
        }
    }
}