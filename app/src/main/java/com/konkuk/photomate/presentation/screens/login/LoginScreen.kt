package com.konkuk.photomate.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.photomate.R

@Composable
fun LoginScreen(
    onLoginButtonClick: () -> Unit
) {

    val kakako_login_image = painterResource(id = R.drawable.kakao_login_button)
    val app_icon: Painter = painterResource(id = R.drawable.app_icon)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFF) // White
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
            //----------
            Spacer(modifier = Modifier.height(300.dp))
            //------------
//            이 코드는 Button 컴포넌트 내부에 Box 컴포넌트를 생성하고,
//            그 안에 이미지를 배경처럼 보이게 하는 Image 컴포넌트와 버튼의 텍스트를 표시하는 Text 컴포넌트를 배치합니다.
//            Box 컴포넌트를 사용하면, 여러 컴포넌트를 동일한 공간에 배치할 수 있어요.
//
//            Image 컴포넌트의 contentScale 파라미터를 ContentScale.FillBounds로 설정하면,
//            이미지가 전체 공간을 차지하도록 확대/축소됩니다.
//
//            마지막으로, Text 컴포넌트는 Box 컴포넌트의 정중앙에 위치하도록 align 함수를 사용하여 배치됩니다.
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // 전체 너비의 80%를 차지합니다.
                    .fillMaxHeight(0.15f) // 전체 높이의 15%를 차지합니다.
                    .clickable {
                        onLoginButtonClick()
                    }
                    .background(color = Color.Transparent)
                    .graphicsLayer {
                        clip = true
                    }
            ) {
                Image(
                    painter = kakako_login_image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(
        onLoginButtonClick = {}
    )
}