package com.konkuk.photomate.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileScreen() {
    // 메인 - 마이페이지입니다
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .background(Color.White)
            ) {

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
                    .background(Color.Gray)
            ) {

            }
        }
    }
}