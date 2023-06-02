package com.konkuk.photomate.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konkuk.photomate.R

@Composable
fun PhotoMateFloatingActionButton(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color = Color.Black,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = CircleShape
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "상세 주소 선택 아이콘" // 원래는 string resource로 따로 관리해줘야 합니다.
        )
    }
}