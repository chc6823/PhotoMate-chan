@file:OptIn(ExperimentalMaterial3Api::class)

package com.konkuk.photomate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.konkuk.photomate.presentation.components.PhotoMateBottomBar
import com.konkuk.photomate.presentation.components.Screen
import com.konkuk.photomate.presentation.screens.home.HomeScreen
import com.konkuk.photomate.presentation.screens.notification.NotificationScreen
import com.konkuk.photomate.presentation.screens.profile.ProfileScreen
import com.konkuk.photomate.ui.theme.PhotoMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoMateTheme {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.White,
                        darkIcons = true // 저희는 다크모드 디자인이 없기 때문에 시스템바 아이콘 색상은 항상 검정..
                    )
                }

                Scaffold(
                    bottomBar = {
                        PhotoMateBottomBar(navController = navController)
                    },
                    floatingActionButton = {
                        if (currentRoute == Screen.Home.route) {
                            // TODO("플로팅 액션 버튼 추가")
                        }
                    }
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            HomeScreen()
                        }
                        composable(route = Screen.Notification.route) {
                            val isChecked = remember { mutableStateOf(false) }

                            NotificationScreen(
                                isChecked = isChecked.value,
                                onCheckedChange = { newValue ->
                                    isChecked.value = newValue
                                }
                            )
                        }
                        composable(route = Screen.Profile.route) {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}
