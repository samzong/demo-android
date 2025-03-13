package com.sample.demo.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.demo.R
import com.sample.demo.presentation.navigation.Screen
import com.sample.demo.presentation.screens.documentation.DocumentationContent
import com.sample.demo.presentation.screens.profile.ProfileScreen
import com.sample.demo.presentation.screens.websearch.WebSearchContent

@Composable
fun MainScreen(
    onLogout: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = stringResource(R.string.tab_web_search)
                        )
                    },
                    selected = uiState.selectedTabRoute == Screen.MainTab.WebSearch.route,
                    onClick = { viewModel.onTabSelected(Screen.MainTab.WebSearch.route) }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = stringResource(R.string.tab_documentation)
                        )
                    },
                    selected = uiState.selectedTabRoute == Screen.MainTab.Documentation.route,
                    onClick = { viewModel.onTabSelected(Screen.MainTab.Documentation.route) }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = stringResource(R.string.tab_profile)
                        )
                    },
                    selected = uiState.selectedTabRoute == Screen.MainTab.Profile.route,
                    onClick = { viewModel.onTabSelected(Screen.MainTab.Profile.route) }
                )
            }
        }
    ) { paddingValues ->
        when (uiState.selectedTabRoute) {
            Screen.MainTab.WebSearch.route -> {
                WebSearchContent(
                    modifier = Modifier.padding(paddingValues),
                    url = "https://www.baidu.com"
                )
            }

            Screen.MainTab.Documentation.route -> {
                DocumentationContent(
                    modifier = Modifier.padding(paddingValues),
                    url = "https://docs.daocloud.io"
                )
            }

            Screen.MainTab.Profile.route -> {
                ProfileScreen(
                    username = uiState.username,
                    deviceId = uiState.deviceId,
                    onLogout = {
                        viewModel.onLogout()
                        onLogout()
                    }
                )
            }
        }
    }
} 