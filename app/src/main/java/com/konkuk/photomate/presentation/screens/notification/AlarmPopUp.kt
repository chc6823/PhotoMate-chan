package com.konkuk.photomate.presentation.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.konkuk.photomate.R

@Composable
fun AlarmPopUp(
    modifier: Modifier = Modifier,
    // 상대방 기기 정보
    name: String,
    address: String,
    score: Float,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .width(320.dp)
                .height(200.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(top = 20.dp)
            ) {
                Column {
                    Text(
                        text = "매칭 요청",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 14.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Row(
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2021/07/26/bbe2deec-47db-4866-a8ce-abb39a0a181d.jpg")
                                .crossfade(true)
                                .build(),
                            contentScale = ContentScale.Crop,
                            contentDescription = "프로필 사진"
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Row(
                                modifier = modifier
                            ) {
                                Text(
                                    text = name,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 2.dp),
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "평점",
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 2.dp, end = 5.dp)
                                )
                                Text(
                                    text = score.toString(),
                                    fontSize = 12.sp,
                                    color = Color.Red,
                                    modifier = Modifier.padding(top = 1.dp, bottom = 2.dp)
                                )
                            }
                            Text(
                                text = address,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "> 단체 사진을 정말 잘 찍어주세요 ㅎㅎ",
                                fontSize = 12.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(top = 1.dp, bottom = 2.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 22.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,
                                    contentColor = Color(0xFFFFD044)
                                ),
                                elevation = null,
                                onClick = {
                                    /* 수락 버튼 클릭 시 수행할 작업 정의 */
                                    onConfirmRequest()
                                }
                            ) {
                                Text(
                                    text = "수락",
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Divider(
                                color = Color.Gray,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                elevation = null,
                                onClick = {
                                    /* 거절 버튼 클릭 시 수행할 작업 정의 */
                                    onDismissRequest()
                                }
                            ) {
                                Text(
                                    text = "거절",
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                        Divider(
                            color = Color.Gray,
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AlarmPopUpPreview() {
    AlarmPopUp(
        name = "김건국",
        address = "서울시 광진구 화양동",
        score = 4.5f,
        onDismissRequest = {},
        onConfirmRequest = {}
    )
}
