package com.sample.demo.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // 模拟网络请求延迟
            kotlinx.coroutines.delay(1000)
            
            // 简单的验证逻辑，实际应用中应该调用 API
            val isLoginSuccessful = _uiState.value.username.isNotBlank() && _uiState.value.password.isNotBlank()
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    isLoginSuccessful = isLoginSuccessful,
                    errorMessage = if (!isLoginSuccessful) "用户名或密码不正确" else null
                )
            }
        }
    }
    
    fun resetLoginState() {
        _uiState.update { 
            it.copy(
                isLoginSuccessful = false,
                errorMessage = null
            )
        }
    }
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val errorMessage: String? = null
) 