package com.sample.demo.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.demo.data.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username, errorMessage = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            // 模拟网络请求延迟
            kotlinx.coroutines.delay(1000)

            // 使用固定的用户名和密码进行验证
            val isLoginSuccessful =
                _uiState.value.username == "user" && _uiState.value.password == "password"

            if (isLoginSuccessful) {
                // 保存登录状态
                userPreferencesRepository.saveLoginState(true, _uiState.value.username)
            }
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    isLoginSuccessful = isLoginSuccessful,
                    errorMessage = if (!isLoginSuccessful && (it.username.isNotEmpty() || it.password.isNotEmpty()))
                        "用户名或密码不正确"
                    else
                        null
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