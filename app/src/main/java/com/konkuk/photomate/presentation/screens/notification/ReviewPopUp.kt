package com.konkuk.photomate.presentation.screens.notification

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog

@Composable
fun ReviewPopUp(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Alarm") },
            text = { Text("이대로 이뷰를 남시기겠습니까?") },
            confirmButton = {
                TextButton(onClick = { /* 확인 버튼을 누르면 수행할 작업 */ }) {
                    Text("확인", color = Color.Yellow)
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text("취소")
                }
            }
        )
    }


}

@Preview
@Composable
fun ReviewPopUpPreview() {
    ReviewPopUp(
        onDismissRequest = {},
        onConfirmRequest = {}
    )
}