package com.konkuk.photomate.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val name: String,
    val route: String,
    val icon: ImageVector
) {
    object Home : Screen("홈", "home", Icons.Rounded.Home)
    object Notification : Screen("알림", "notification", Icons.Rounded.Notifications)
    object Profile : Screen("마이", "profile", Icons.Rounded.Person)
}