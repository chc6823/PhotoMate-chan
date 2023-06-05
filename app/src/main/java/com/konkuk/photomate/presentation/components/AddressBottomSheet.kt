package com.konkuk.photomate.presentation.components

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.konkuk.photomate.R
import com.konkuk.photomate.presentation.screens.notification.AlarmPopUp
import java.util.UUID

@Composable
fun AddressBottomSheet(
    modifier: Modifier = Modifier,
    address: String,
    onConfirmRequest: () -> Unit,
    dialogOpenState: MutableState<Boolean>
) {
    val context = LocalContext.current
    val bluetoothAdapter = remember { BluetoothAdapter.getDefaultAdapter() }
    val bluetoothEnabled = remember { mutableStateOf(bluetoothAdapter?.isEnabled == true) }
    var requestBluetoothFlag = remember { mutableStateOf(false) }
//    val requestBluetoothEnable = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == RESULT_OK) {
//            bluetoothEnabled.value = true
//            findAndConnectClosestDevice(context, dialogOpenState)
//        } else {
//            Toast.makeText(context, "Bluetooth activation denied.", Toast.LENGTH_SHORT).show()
//        }
//    }

    Column {
        Box(
            modifier = modifier
                .background(color = Color.White)
                .padding(20.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.move_pin_to_where_you_want_to_photomate),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Layout(
                    content = {
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                imageVector = Icons.Default.Circle,
                                contentDescription = "빨간 점",
                                tint = Color.Red
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = address,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.Close,
                                contentDescription = "삭제 버튼",
                                tint = Color.DarkGray
                            )
                        }
                        Divider()
                    },
                    measurePolicy = { measurable, constraints ->
                        val rowPlaceable = measurable[0].measure(constraints)
                        val dividerPlaceable =
                            measurable[1].measure(constraints.copy(maxWidth = rowPlaceable.width))

                        layout(rowPlaceable.width, rowPlaceable.height + dividerPlaceable.height) {
                            rowPlaceable.placeRelative(0, 0)
                            dividerPlaceable.placeRelative(0, rowPlaceable.height)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Default.GpsFixed,
                        contentDescription = "삭제 버튼",
                        tint = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.set_to_current_locaton),
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.main_color))
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp)
                .clickable {
                    if (bluetoothEnabled.value) {
                        findAndConnectClosestDevice(context, dialogOpenState, {
                            AlarmPopUp(device.name, device.address, )
                        })
                    } else {
                        val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        requestBluetoothEnable.launch(enableBluetoothIntent)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.request_matching),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


//@Preview
//@Composable
//fun AddressBottomSheetPreview() {
//    AddressBottomSheet(
//        address = "경북 포항시 북구 두호동 685-1",
//        onConfirmRequest = {}
//    )
//}

private fun findAndConnectClosestDevice(
    context: Context,
    dialogOpenState: MutableState<Boolean>,
    onShowAlarm: () -> Unit
) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    // Bluetooth 지원 여부 확인
    if (bluetoothAdapter == null) {
        Toast.makeText(context, "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show()
        return
    }

    // Bluetooth 활성화 여부 확인
    if (!bluetoothAdapter.isEnabled) {
        Toast.makeText(context, "Please enable Bluetooth.", Toast.LENGTH_SHORT).show()
        return
    }

    // 블루투스 연결 권한 확인
    val hasPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.BLUETOOTH_CONNECT
    ) == PackageManager.PERMISSION_GRANTED

    if (hasPermission) {
        // 페어링된 장치 중에서 가까운 상대방 찾기
        val pairedDevices = bluetoothAdapter.bondedDevices
        if (pairedDevices.isEmpty()) {
            Toast.makeText(context, "No paired devices found.", Toast.LENGTH_SHORT).show()
            return
        }

        pairedDevices?.forEach { device ->
            // 상대방에게 블루투스로 연결하고 AlarmPopUp() 함수 호출
            val socket: BluetoothSocket? = device.createRfcommSocketToServiceRecord(MY_UUID)

            socket?.connect()
            // 연결 성공
            val name = device.name
            val address = device.address
            val score = "10"
            val modifier = Modifier
            dialogOpenState.value = true
            //AlarmPopUp(name, address, score, modifier, dialogOpenState)
            onShowAlarm()
            return
        }
    }
}