package com.sample.demo.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    // 后续可以添加更多屏幕
} 