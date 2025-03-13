package com.sample.demo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.demo.presentation.screens.login.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // 登录成功后导航到主页面，暂时未实现
                    // navController.navigate(Screen.Home.route)
                }
            )
        }
        // 其他页面路由将在后续添加
    }
} 