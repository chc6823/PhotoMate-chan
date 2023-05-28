package com.konkuk.photomate.presentation.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.konkuk.photomate.R

@Composable
fun AlarmPopUp(
    modifier: Modifier = Modifier
) {
    // PopUpPreview()
}

@Composable
fun AlarmPopUp(
    name: String,
    address: String,
    score: String,
    modifier: Modifier,
    dialogOpenState: MutableState<Boolean>
) {
    Dialog(
        onDismissRequest = { dialogOpenState.value = false }
    ) {
        if (dialogOpenState.value) {
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
                    Column() {
                        Text(
                            text = "매칭 요청",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 14.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Row() {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                                    .padding(top = 2.dp, end = 8.dp, start = 9.dp)
                            )
                            Column() {
                                Text(
                                    text = name,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Text(
                                    text = address,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Row(
                                    modifier = modifier
                                ) {
                                    Text(
                                        text = "평점",
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(bottom = 2.dp, end = 5.dp)
                                    )
                                    Text(
                                        text = score,
                                        fontSize = 12.sp,
                                        color = Color.Red,
                                        modifier = Modifier.padding(top = 1.dp, bottom = 2.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth()
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
                                        .height(44.dp)
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
}
