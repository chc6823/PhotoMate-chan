package com.konkuk.photomate.presentation.components

import android.graphics.fonts.FontStyle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.photomate.R

@Composable
fun AddressBottomSheet(
    modifier: Modifier = Modifier,
    address: String,
    onConfirmRequest: () -> Unit,
    isBluetoothEnabled: Boolean,
    onFindAndConnectClosestDevice: () -> Unit,
    onDissMissBluetoothRequest: () -> Unit
) {
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
                    if (isBluetoothEnabled) {
//                        findAndConnectClosestDevice(context, dialogOpenState, {
//                            AlarmPopUp(device.name, device.address)
//                        })
                        onFindAndConnectClosestDevice()
                    } else {
//                        val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                        requestBluetoothEnable.launch(enableBluetoothIntent)
                        onDissMissBluetoothRequest()
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


@Preview
@Composable
fun AddressBottomSheetPreview() {
    AddressBottomSheet(
        address = "경북 포항시 북구 두호동 685-1",
        onConfirmRequest = {},
        isBluetoothEnabled = false,
        onDissMissBluetoothRequest = {},
        onFindAndConnectClosestDevice = {}
    )
}