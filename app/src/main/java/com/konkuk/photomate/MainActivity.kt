@file:OptIn(ExperimentalMaterial3Api::class)

package com.konkuk.photomate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.konkuk.photomate.presentation.components.AddressBottomSheet
import com.konkuk.photomate.presentation.components.LocationButton
import com.konkuk.photomate.presentation.components.Screen
import com.konkuk.photomate.presentation.components.PhotoMateBottomBar
import com.konkuk.photomate.presentation.components.PhotoMateFloatingActionButton
import com.konkuk.photomate.presentation.screens.home.HomeScreen
import com.konkuk.photomate.presentation.screens.home.HomeViewModel
import com.konkuk.photomate.presentation.screens.notification.NotificationScreen
import com.konkuk.photomate.presentation.screens.profile.ProfileScreen
import com.konkuk.photomate.ui.theme.PhotoMateTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoMateTheme {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val scope = rememberCoroutineScope()
                val sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    skipHalfExpanded = true
                )
                val snackBarHostState = remember { SnackbarHostState() }
                var lastBackPressedTime by remember { mutableStateOf(0L) }

                val homeViewModel = hiltViewModel<HomeViewModel>()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.White,
                        darkIcons = true // 저희는 다크모드 디자인이 없기 때문에 시스템바 아이콘 색상은 항상 검정..
                    )
                }

                BackHandler(sheetState.isVisible) {
                    scope.launch {
                        sheetState.hide()
                    }
                }

                BackHandler(!sheetState.isVisible) {
                    val currentBackPressedTime = System.currentTimeMillis()
                    if (currentBackPressedTime - lastBackPressedTime > 1500L) {
                        lastBackPressedTime = currentBackPressedTime
                        scope.launch {
                            val job = launch {
                                snackBarHostState.showSnackbar(
                                    message = "뒤로가기를 한번 더 누르면 종료합니다.",
                                    duration = SnackbarDuration.Indefinite
                                )
                            }
                            delay(1500)
                            job.cancel()
                        }
                    } else {
                        finish()
                    }
                }

                ModalBottomSheetLayout(
                    sheetState = sheetState,
                    sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    sheetContent = {
                        AddressBottomSheet(
                            address = "경북 포항시 북구 두호동 685-1",
                            onConfirmRequest = {}
                        )
                    }
                ) {
                    Scaffold(
                        bottomBar = {
                            PhotoMateBottomBar(navController = navController)
                        },
                        floatingActionButton = {
                            if (currentRoute == Screen.Home.route) {
                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    LocationButton(
                                        onClick = {
                                            homeViewModel.onCurrentLocationClick()
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    PhotoMateFloatingActionButton(
                                        containerColor = colorResource(id = R.color.main_color),
                                        onClick = {
                                            scope.launch { sheetState.show() }
                                        }
                                    )
                                }
                            }
                        }
                    ) { padding ->
                        NavHost(
                            modifier = Modifier.padding(padding),
                            navController = navController,
                            startDestination = Screen.Home.route
                        ) {
                                composable(route = Screen.Home.route) {
                                val state by homeViewModel.state.collectAsStateWithLifecycle()
                                HomeScreen(
                                    state = state
                                )
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
//                            composable(route = Screen.mypage.route) {
//                                ProfileScreen()
//                            }
                        }
                    }
                }
            }
        }
    }
}
