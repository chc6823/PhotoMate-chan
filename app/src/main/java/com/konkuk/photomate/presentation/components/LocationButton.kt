package com.konkuk.photomate.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LocationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = Color.White,
        contentColor = Color.DarkGray,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Outlined.MyLocation,
            contentDescription = "현위치 설정 아이콘" // 원래는 string resource로 따로 관리해줘야 합니다.
        )
    }
}