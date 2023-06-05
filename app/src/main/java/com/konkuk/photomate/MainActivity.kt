@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.konkuk.photomate

import android.Manifest
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.konkuk.photomate.presentation.components.AddressBottomSheet
import com.konkuk.photomate.presentation.components.LocationButton
import com.konkuk.photomate.presentation.components.PhotoMateBottomBar
import com.konkuk.photomate.presentation.components.PhotoMateFloatingActionButton
import com.konkuk.photomate.presentation.components.Screen
import com.konkuk.photomate.presentation.screens.home.HomeScreen
import com.konkuk.photomate.presentation.screens.home.HomeViewModel
import com.konkuk.photomate.presentation.screens.login.LoginScreen
import com.konkuk.photomate.presentation.screens.matching.MatchingScreen
import com.konkuk.photomate.presentation.screens.notification.AlarmPopUp
import com.konkuk.photomate.presentation.screens.notification.NotificationScreen
import com.konkuk.photomate.presentation.screens.profile.ProfileReviewScreen
import com.konkuk.photomate.presentation.screens.profile.ProfileScreen
import com.konkuk.photomate.presentation.screens.profileModification.ProfileModificationScreen
import com.konkuk.photomate.presentation.screens.searching.SearchingScreen
import com.konkuk.photomate.ui.theme.PhotoMateTheme
import com.konkuk.photomate.util.KakaoLoginManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var hasBluetoothPermission by mutableStateOf(false)
    private var socket: BluetoothSocket? = null

    @Inject
    lateinit var kakaoLoginManager: KakaoLoginManager

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_SCAN
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        hasBluetoothPermission = true
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        // Display a Snackbar or some sort of feedback for the user to explain why we need the permission.
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Bluetooth permissions are needed for this app to function properly",
                            Snackbar.LENGTH_LONG
                        ).show()

                        token?.continuePermissionRequest()
                    }
                })
                .check()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoMateTheme {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val scope = rememberCoroutineScope()
                val sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    skipHalfExpanded = true
                )
                val snackBarHostState = remember { SnackbarHostState() }
                var lastBackPressedTime by remember { mutableStateOf(0L) }

                val homeViewModel = hiltViewModel<HomeViewModel>()
                var isPopUpAlarmShown by remember { mutableStateOf(false) }
                var currentAddress by remember { mutableStateOf("서울 광진구 능동로 120") }

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.White,
                        darkIcons = true // 저희는 다크모드 디자인이 없기 때문에 시스템바 아이콘 색상은 항상 검정..
                    )
                }

                LaunchedEffect(Unit) {
                    Timber.tag("onseok").d(hasBluetoothPermission.toString())
                }

                BackHandler(sheetState.isVisible) {
                    scope.launch {
                        sheetState.hide()
                    }
                }

                BackHandler(!sheetState.isVisible) {
                    val currentBackPressedTime = System.currentTimeMillis()
                    if (currentBackPressedTime - lastBackPressedTime > 1500L) {
                        lastBackPressedTime = currentBackPressedTime
                        scope.launch {
                            val job = launch {
                                snackBarHostState.showSnackbar(
                                    message = "뒤로가기를 한번 더 누르면 종료합니다.",
                                    duration = SnackbarDuration.Indefinite
                                )
                            }
                            delay(1500)
                            job.cancel()
                        }
                    } else {
                        finish()
                    }
                }

                if (isPopUpAlarmShown) {
                    AlarmPopUp(
                        name = "건국이", // 상대방에게 넘길 나의 이름
                        address = "건대", // 상대방에게 넘길 나의 주소
                        score = 4.3f, // 상대방에게 넘길 나의 평점
                        onDismissRequest = {
                            isPopUpAlarmShown = false
                        },
                        onConfirmRequest = {
                            isPopUpAlarmShown = false
                            navController.navigate("matching")
                        }
                    )
                }

                ModalBottomSheetLayout(
                    sheetState = sheetState,
                    sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    sheetContent = {
                        if (currentRoute == Screen.Home.route) {
                            AddressBottomSheet(
                                address = currentAddress,
                                onConfirmRequest = {
                                    navController.navigate("searching?address=${currentAddress}")
                                },
                                onFindAndConnectClosestDevice = {
                                    Timber.tag("onseok").d("클릭 됨")
                                    findAndConnectClosestDevice(
                                        context = this@MainActivity,
                                        hasPermission = hasBluetoothPermission,
                                        onShowModalBottomSheet = {
                                            scope.launch {
                                                sheetState.show()
                                            }
                                        },
                                        onShowToastMessage = {
                                            // TODO 스낵바 scaffold에 붙이기
                                        },
                                        onShowAlarm = {
                                            isPopUpAlarmShown = true
                                        }
                                    )
                                },
                                onDissMissBluetoothRequest = {
                                },
                                isBluetoothEnabled = false
                            )
                        }
                    }
                ) {
                    Scaffold(
                        bottomBar = {
                            if (currentRoute != "modification"
                                && currentRoute != "matching"
                                && currentRoute?.contains("searching") != true
                                && currentRoute != "login"
                            ) {
                                PhotoMateBottomBar(navController = navController)
                            }
                        },
                        floatingActionButton = {
                            if (currentRoute == Screen.Home.route) {
                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    LocationButton(
                                        onClick = {
//                                            homeViewModel.onCurrentLocationClick()
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    PhotoMateFloatingActionButton(
                                        containerColor = colorResource(id = R.color.main_color),
                                        onClick = {
                                            scope.launch {
//                                                homeViewModel.getCurrentLocation(location = Location())
                                                sheetState.show()
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { padding ->
                        NavHost(
                            modifier = Modifier.padding(padding),
                            navController = navController,
                            startDestination = "login"
                        ) {
                            composable(route = Screen.Home.route) {
                                val state by homeViewModel.state.collectAsStateWithLifecycle()
                                HomeScreen(
                                    state = state
                                )
                            }
                            composable(route = Screen.Notification.route) {
                                val isChecked = remember { mutableStateOf(true) }
                                val requestAlarm = remember { mutableStateOf(1) }
                                NotificationScreen(
                                    isChecked = isChecked.value,
                                    onCheckedChange = { newValue ->
                                        isChecked.value = newValue
                                    },
                                    requestAlarm = requestAlarm
                                )
                            }
                            composable(route = Screen.Profile.route) {
                                ProfileScreen(
                                    navController = navController
                                )
                            }
                            composable(route = "matching") {
                                MatchingScreen(
                                    name = "마블리",
                                    rating = 4.3f,
                                    navController = navController,
                                    representativeImages = listOf(
                                        "https://mblogthumb-phinf.pstatic.net/MjAxODAzMTZfMTk5/MDAxNTIxMTI5MjMyMjk0.DlTG5nDHu4wKAnX15KlEgEJk6h2NfXH_fmykI0AKoT0g.XeVgZnGOI-2H5GW99WJJnD8u9ZyrRDo-vS-EUQQ3Cs0g.JPEG.ju4349/%EC%BA%84%EB%B3%B4%EB%94%94%EC%95%84_%EC%88%98%ED%8F%89_%EB%A7%9E%EC%B6%98_%ED%9B%84.jpg?type=w800",
                                        "https://mp-seoul-image-production-s3.mangoplate.com/mango_pick/1opyqrgnyw1ipe.jpg?fit=around|600:*&crop=600:*;*,*&output-format=jpg&output-quality=80",
                                        "https://t1.daumcdn.net/cfile/tistory/99F6363359891F943B",
                                        "https://datacdn.ibtravel.co.kr/files/2023/05/10114536/jje-00490-2-1.jpg",
                                        "https://cdn.cctoday.co.kr/news/photo/202106/2144368_577455_2410.jpg",
                                        "https://ak-d.tripcdn.com/images/1mi312224udcagpps6FB8_W_670_10000.jpg?proc=source/trip",
                                        "https://phinf.pstatic.net/checkout.phinf/20191126_16/1574772152017D3oGo_JPEG/DDFA6470-AB72-44AB-90E5-F7EB31420795.jpeg",
                                        "https://api.cdn.visitjeju.net/photomng/imgpath/202206/03/ddc76b48-8667-4cd9-bd18-2fa5d801310b.jpg",
                                        "https://blog.kakaocdn.net/dn/o1KIw/btqu9mflPY6/rGk1mM3iugV1c6jj9Z3E80/img.jpg"
                                    )
                                )
                            }
                            composable(route = "modification") {
                                ProfileModificationScreen(navController = navController)
                            }
                            composable(
                                route = "searching?address={address}",
                                arguments = listOf(
                                    navArgument("address") {
                                        defaultValue = ""
                                        type = NavType.StringType
                                    }
                                )
                            ) {
                                val address = navBackStackEntry?.arguments?.getString("address")
                                address?.let {
                                    SearchingScreen(
                                        navController = navController,
                                        address = it,
                                        onNavigateToMatching = {
                                            navController.navigate("matching")
                                        }
                                    )
                                }
                            }
                            composable(route = "profileReview") {
                                ProfileReviewScreen(navController = navController)
                            }
                            composable(route = "login") {
                                LoginScreen(
                                    onLoginButtonClick = {
                                        Timber.tag("kakao_login").d("클릭")
                                        kakaoLoginManager.startKakaoLogin {
                                            Timber.tag("kakao_login").d(it)
                                            navController.navigate(Screen.Home.route)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun findAndConnectClosestDevice(
        context: Context,
        hasPermission: Boolean,
        onShowModalBottomSheet: () -> Unit,
        onShowToastMessage: (String) -> Unit,
        onShowAlarm: () -> Unit
    ) {
        val bluetoothManager =
            context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter
        val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

        // Bluetooth 지원 여부 확인
        if (bluetoothAdapter == null) {
            onShowToastMessage("This device does not support Bluetooth.")
            Timber.tag("onseok").d("This device does not support Bluetooth.")
            return
        }

        // Bluetooth 활성화 여부 확인
        if (!bluetoothAdapter.isEnabled) {
            onShowToastMessage("Please enable Bluetooth.")
            Timber.tag("onseok").d("Please enable Bluetooth.")
            return
        }

        if (hasPermission) {
            // 페어링된 장치 중에서 가까운 상대방 찾기
            try {
                val pairedDevices = bluetoothAdapter.bondedDevices
                if (pairedDevices.isEmpty()) {
                    Timber.tag("onseok").d("No paired devices found.")
                    onShowToastMessage("No paired devices found.")
                    return
                }

                pairedDevices?.forEach { device ->
                    Timber.tag("onseok").d(device.name)
                    socket = device.createRfcommSocketToServiceRecord(uuid)

                    // Check if the device is already connected
                    if (!socket?.isConnected!!) {
                        // Run the blocking connect() call on another thread to avoid freezing the app
                        Thread {
                            try {
                                socket?.connect()
                            } catch (e: IOException) {
                                Timber.e(e, "Error occurred while connecting to device.")
                            }
                        }.start()
                    }

                    onShowModalBottomSheet()
                    onShowAlarm()
                    return
                }
            } catch (securityException: SecurityException) {
                Timber.e(securityException)
            } finally {
                try {
                    socket?.close()
                } catch (e: IOException) {
                    Timber.e(e, "Error occurred while closing the socket.")
                }
            }
        }
    }
}
