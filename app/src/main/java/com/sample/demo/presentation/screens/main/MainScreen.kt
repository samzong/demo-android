package com.sample.demo.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sample.demo.R
import com.sample.demo.presentation.navigation.Screen
import com.sample.demo.presentation.screens.documentation.DocumentationContent
import com.sample.demo.presentation.screens.websearch.WebSearchContent

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(Screen.MainTab.WebSearch.route) }

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
                    selected = selectedTab == Screen.MainTab.WebSearch.route,
                    onClick = { selectedTab = Screen.MainTab.WebSearch.route }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = stringResource(R.string.tab_documentation)
                        )
                    },
                    selected = selectedTab == Screen.MainTab.Documentation.route,
                    onClick = { selectedTab = Screen.MainTab.Documentation.route }
                )
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
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
        }
    }
} 