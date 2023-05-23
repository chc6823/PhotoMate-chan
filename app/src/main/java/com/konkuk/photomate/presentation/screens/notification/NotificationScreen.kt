package com.konkuk.photomate.presentation.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column {
        ToolBar()

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.25f))
                .padding(31.dp)
        ) {
            Container(isChecked = isChecked, onCheckedChange = onCheckedChange)
        }
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
        Text(
            text = "알림 설정",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Container(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .width(340.dp)
                .height(100.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Column() {
                Text(
                    text = "알림 설정",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "매칭 요청 알림을 ON/OFF",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked ->
                    onCheckedChange(isChecked)
                    if (isChecked) {
                        println("알림 설정이 활성화되었습니다.")
                        enableBluetooth()
                    } else {
                        println("알림 설정이 비활성화되었습니다.")
                        disableBluetooth()
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFFFFDA00),
                    uncheckedThumbColor = Color.Gray
                ),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .scale(1.6f)
            )
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    val modifier: Modifier = Modifier
    val isChecked = remember { mutableStateOf(false) }

    Column() {
        ToolBar()
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFD9D9D9))
                .padding(31.dp)
        ) {
            Container(isChecked = isChecked.value,
                onCheckedChange = { newValue ->
                    isChecked.value = newValue
                })
        }
    }
}

@Preview
@Composable
fun ContainerPreview() {
    val isChecked = remember { mutableStateOf(false) }

    Container(
        isChecked = isChecked.value,
        onCheckedChange = { newValue ->
            isChecked.value = newValue
        }
    )
}

private fun enableBluetooth() {
    // 블루투스를 켜는 동작을 수행하는 코드 작성
    // 예: BluetoothAdapter.enable()
}

private fun disableBluetooth() {
    // 블루투스를 끄는 동작을 수행하는 코드 작성
    // 예: BluetoothAdapter.disable()
}