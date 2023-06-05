package com.konkuk.photomate.presentation.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.konkuk.photomate.R

@Composable
fun ProfileReviewScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column {
        ToolBar()
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.25f))
                .padding(31.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Container(painterResource(R.drawable.konkuk_koo), "건국이",
                    "친절하게 찍어주셔서 감사합니다. 좋은 하루 되세요~", "5")
                Container(painterResource(R.drawable.mavley), "마블리",
                    "단체 사진을 잘 찍어주셔서 좋은 사진 건지고 갑니다~", "4")
            }
        }
    }
    BackHandler {
        navController.navigateUp()
    }
}

@Composable
fun ToolBar() {
    val modifier: Modifier = Modifier
    Box(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 34.dp, top = 15.dp)
    ) {
        androidx.compose.material.Text(
            text = "모아보기",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Container(
    image: Painter,
    nickname: String,
    review: String,
    score: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .width(340.dp)
                .height(140.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Row() {
                Image(
                    painter = image,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(70.dp, 70.dp)
                        .padding(top = 10.dp, end = 5.dp)
                )
                Column() {
                    androidx.compose.material.Text(
                        text = nickname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    androidx.compose.material.Text(
                        text = review,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Row() {
                        androidx.compose.material.Text(
                            text = "평점",
                            color = Color.Black,
                            modifier = Modifier.padding(end = 1.dp)
                        )
                        Spacer(modifier = Modifier.padding(1.dp))
                        androidx.compose.material.Text(
                            text = score,
                            color = Color.Red,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun ScreenPreview() {
    val modifier: Modifier = Modifier
    Column() {
        ToolBar()
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFD9D9D9))
                .padding(31.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Container(painterResource(R.drawable.konkuk_koo), "건국이",
                    "친절하게 찍어주셔서 감사합니다. 좋은 하루 되세요~", "5")
                Container(painterResource(R.drawable.mavley), "마블리",
                    "단체 사진을 잘 찍어주셔서 너무 좋았어요. 덕분에 좋은 사진 건지고 갑니다~", "4")
            }
        }
    }
}

@Preview
@Composable
fun ContainerPreview() {
    Container(painterResource(R.drawable.mavley), "마블리",
        "단체 사진을 잘 찍어주셔서 너무 좋았어요. 덕분에 좋은 사진 건지고 갑니다~", "4")
}