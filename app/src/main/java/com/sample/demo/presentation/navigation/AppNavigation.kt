package com.sample.demo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.demo.presentation.screens.documentation.DocumentationScreen
import com.sample.demo.presentation.screens.login.LoginScreen
import com.sample.demo.presentation.screens.main.MainScreen
import com.sample.demo.presentation.screens.websearch.WebSearchScreen

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
                    // 登录成功后导航到主页面
                    navController.navigate(Screen.Main.route) {
                        // 清除回退栈，防止用户返回登录页面
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen()
        }

        // 保留单独的页面路由，以便从其他地方直接导航
        composable(route = Screen.MainTab.WebSearch.route) {
            WebSearchScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = Screen.MainTab.Documentation.route) {
            DocumentationScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
} 