package com.konkuk.photomate.presentation.screens.searching

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.photomate.R
import com.konkuk.photomate.util.components.PhotoMateLoading
import kotlinx.coroutines.delay

@Composable
fun SearchingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    address: String,
    onNavigateToMatching: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(10000)
        onNavigateToMatching()
    }

    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                    },
                    imageVector = Icons.Default.Home,
                    contentDescription = "홈 버튼"
                )
                Text(
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                    },
                    text = "요청 중단",
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            PhotoMateLoading()
            Text(
                text = "포토메이트 찾는중",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.Flag,
                    contentDescription = "현재 위치 확인하기 버튼",
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "현재 위치 확인하기",
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = address,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
//            Text(
//                text = "근처 2명의 포토메이트에게 알람을 보냈어요.",
//                style = TextStyle(
//                    color = Color.LightGray
//                )
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Surface(
//                modifier = Modifier
//                    .height(60.dp)
//                    .fillMaxWidth(0.75f),
//                shape = RoundedCornerShape(20.dp),
//                color = Color.White,
//                border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.main_color))
//            ) {
//                Row(
//                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        modifier = Modifier.size(50.dp),
//                        painter = painterResource(id = R.drawable.ic_logo),
//                        contentDescription = "포토메이트 로고"
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Icon(
//                        modifier = Modifier.size(50.dp),
//                        painter = painterResource(id = R.drawable.ic_logo),
//                        contentDescription = "포토메이트 로고"
//                    )
//                }
//            }
        }
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.main_color))
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "매칭까지 약 4분",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview
@Composable
fun SearchingScreenPreview() {
    SearchingScreen(
        navController = rememberNavController(),
        address = "건대",
        onNavigateToMatching = {}
    )
}