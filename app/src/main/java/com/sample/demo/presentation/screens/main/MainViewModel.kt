package com.sample.demo.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.demo.data.UserPreferencesRepository
import com.sample.demo.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.username.collect { username ->
                _uiState.update { it.copy(username = username) }
            }
        }

        // 获取设备ID
        val deviceId = userPreferencesRepository.getDeviceId()
        _uiState.update { it.copy(deviceId = deviceId) }
    }

    fun onTabSelected(tabRoute: String) {
        _uiState.update { it.copy(selectedTabRoute = tabRoute) }
    }

    fun onLogout() {
        viewModelScope.launch {
            userPreferencesRepository.clearLoginState()
        }
    }
}

data class MainUiState(
    val username: String = "",
    val deviceId: String = "",
    val selectedTabRoute: String = Screen.MainTab.WebSearch.route
) 