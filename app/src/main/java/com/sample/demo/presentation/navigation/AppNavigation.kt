package com.sample.demo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.demo.data.UserPreferencesRepository
import com.sample.demo.presentation.screens.documentation.DocumentationScreen
import com.sample.demo.presentation.screens.login.LoginScreen
import com.sample.demo.presentation.screens.main.MainScreen
import com.sample.demo.presentation.screens.websearch.WebSearchScreen

@Composable
fun AppNavigation(
    userPreferencesRepository: UserPreferencesRepository
) {
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }

    // 监听登录状态
    val loginState by userPreferencesRepository.isLoggedIn.collectAsState(initial = false)

    // 当登录状态变化时更新
    LaunchedEffect(loginState) {
        isLoggedIn = loginState
        if (isLoggedIn && navController.currentDestination?.route == Screen.Login.route) {
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        } else if (!isLoggedIn && navController.currentDestination?.route != Screen.Login.route) {
            navController.navigate(Screen.Login.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }
    
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screen.Main.route else Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
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