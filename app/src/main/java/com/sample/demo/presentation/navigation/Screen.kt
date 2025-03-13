package com.sample.demo.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Main : Screen("main")

    // 主页面内的Tab
    sealed class MainTab(val route: String) {
        object WebSearch : MainTab("web_search")
        object Documentation : MainTab("documentation")
    }
} 