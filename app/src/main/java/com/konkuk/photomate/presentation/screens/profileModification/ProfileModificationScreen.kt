package com.konkuk.photomate.presentation.screens.profileModification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.photomate.R

@Composable
fun ProfileModificationScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Surface() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.09f),
                    contentAlignment = Alignment.Center
                ) {
                    // 위쪽 부분
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.x_button), // 닫기 버튼 이미지 리소스
                            contentDescription = "Close",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .height(screenHeight * 0.04f)
                                .width(screenWidth * 0.04f)
                                .clickable {
                                    navController.navigateUp()
                                }
                        )

                        Text(
                            text = "프로필 수정",
                            modifier = Modifier
                                .weight(2f)
                                .align(Alignment.CenterVertically)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(),
                            style = TextStyle(fontWeight = FontWeight.Bold), // Bold
                            fontSize = 25.sp
                        )

                        Text(
                            text = "완료",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(),
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                    }
                }
                Divider()
                //-------------
                Spacer(modifier = Modifier.height(screenHeight * 0.05f))
                //-------------
                // 아래쪽 부분
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(start = screenWidth * 0.02f, end = screenWidth * 0.02f)
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(R.drawable.hoonie),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                            Image(
                                painter = painterResource(R.drawable.camera_icon),
                                contentDescription = "Camera Icon",
                                modifier = Modifier
                                    .height(screenHeight * 0.07f)
                                    .width(screenWidth * 0.07f)
                                    .clickable { /* handle click event */ },
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                    Text(
                        text = "닉네임",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(start = 12.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                    PhotoMateTextField()
                }
            }
        }
    }
}

@Composable
fun PhotoMateTextField() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black
        )
    )

}

@Preview(showBackground = true)
@Composable
fun ProfileModificationScreenPreview() {
    ProfileModificationScreen(
        navController = rememberNavController()
    )
}