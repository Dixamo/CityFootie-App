package com.example.cityfootie_compose.ui.screens.prueba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityfootie_compose.model.prueba.User
import com.example.cityfootie_compose.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getNewUser()
            log.d("UserViewModel", user.toString())
        }
    }
}