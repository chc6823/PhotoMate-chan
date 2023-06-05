package com.konkuk.photomate.presentation.screens.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.konkuk.photomate.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    val app_icon = painterResource(id = R.drawable.app_icon)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFDA00) // #FFDA00,짙은 노란색
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = app_icon,
                contentDescription = "App Icon",
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}