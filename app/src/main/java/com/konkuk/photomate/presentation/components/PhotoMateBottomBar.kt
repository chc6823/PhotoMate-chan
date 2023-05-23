package com.konkuk.photomate.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun PhotoMateBottomBar(
    navController: NavController
) {
    val screens = listOf(Screen.Home, Screen.Notification, Screen.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = Color.White
    ) {
        screens.forEach { screen ->
            val selected = screen.route == currentRoute

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.DarkGray,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.DarkGray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.White
                ),
                label = {
                    Text(
                        text = screen.name,
                        fontWeight = FontWeight.Medium
                    )
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "${screen.name} Icon"
                    )
                }
            )
        }
    }
}