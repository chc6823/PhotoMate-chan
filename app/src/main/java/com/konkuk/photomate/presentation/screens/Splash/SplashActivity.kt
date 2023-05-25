package com.konkuk.photomate.presentation.screens.Splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.photomate.R
import com.konkuk.photomate.presentation.screens.Splash.ui.theme.PhotoMateTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFDA00) // #FFDA00,짙은 노란색
                ) {

                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    val app_icon = painterResource(id = R.drawable.app_icon)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color =  Color(0xFFFFDA00) // #FFDA00,짙은 노란색
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PhotoMateTheme {
        SplashScreen()
    }
}