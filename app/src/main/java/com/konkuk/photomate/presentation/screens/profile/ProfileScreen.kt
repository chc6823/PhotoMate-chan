package com.konkuk.photomate.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.konkuk.photomate.R
import com.konkuk.photomate.presentation.components.Screen

@Composable
fun ProfileScreen(
    onNavigateToModification: () -> Unit
) {
    val hoonieimage: Painter = painterResource(id = R.drawable.hoonie)
    val profilechangebutton: Painter = painterResource(id = R.drawable.profilechangebutton)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val textHeightDp = with(LocalDensity.current) { 30.sp.toDp() }
    val rowHeightDp = (screenHeight * 0.1f)
    val TextpaddingDp = (rowHeightDp - textHeightDp) / 4


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 다른 컴포넌트들
        Column() {
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            //내 정보
            Text(
                text = "내 정보",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier.padding(start = 16.dp) // 왼쪽 padding
            )
            //-----
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            //My profile
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.95f) // 전체 스크린의 90%를 차지하도록 width 설정
                    .fillMaxHeight(0.10f)// 전체 스크린의 10%를 차지하도록 height 설정
                    // start와 end에 전체 스크린의 2.5% padding 설정
                    .padding(start = screenWidth * 0.025f, end = screenWidth * 0.025f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    // 이미지와 텍스트 사이에 간격을 주기 위해 spacedBy 사용
                    horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.01f)
                ) {
                    Image(
                        painter = hoonieimage,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .fillMaxHeight(1f) //전체 Row와 똑같이 height 설정
                            .fillMaxWidth(0.3f) // 전체 뷰 width의 10%를 차지하도록 width 설정
                            .padding(end = screenWidth * 0.03f) // 프로필 사진과 프로필 이름 사이에 약 2% 크기의 padding 설정
                    )

                    Text(
                        text = "후니쓰",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        ),
                        // 텍스트를 수직 가운데로 정렬하기 위해 상하 padding 추가
                        modifier = Modifier.padding(vertical = TextpaddingDp)
                    )

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f) // 전체 이미지 크기 width의 20%를 차지하도록 width 설정
                        .height(50.dp) // 원하는 높이로 설정
                    //.padding(vertical = BoxpaddingDp) // top, bottom 패딩을 추가
                ) {
                    Image(
                        painter = profilechangebutton,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onNavigateToModification()
                            },
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

            //-----
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            //매칭내역,모아보기,버전정보
            //매칭내역
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(start = screenWidth * 0.025f, end = screenWidth * 0.025f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    // 이미지와 텍스트 사이에 간격을 주기 위해 spacedBy 사용
                    horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.01f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.matching),
                        contentDescription = "matchingimage",
                        // menu icon과 menu 사이에 약 2% 크기의 padding 설정
                        Modifier
                            .padding(end = screenWidth * 0.03f)
                            .fillMaxHeight()
                    )
                    Text(
                        text = "매칭 내역",
                        style = TextStyle(
                            fontSize = 30.sp
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(
                                top = screenWidth * 0.03f,
                                bottom = screenWidth * 0.03f
                            )
                            .align(Alignment.CenterVertically)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.mypage_arrow),
                    contentDescription = "mypagearrow",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable { /* Handle Click Here */ }
                )
            }
            //--------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(start = screenWidth * 0.025f, end = screenWidth * 0.025f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    // 이미지와 텍스트 사이에 간격을 주기 위해 spacedBy 사용
                    horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.01f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.moabogi),
                        contentDescription = "matchingimage",
                        // menu icon과 menu 사이에 약 2% 크기의 padding 설정
                        Modifier
                            .padding(end = screenWidth * 0.03f)
                            .fillMaxHeight()
                    )
                    Text(
                        text = "모아 보기",
                        style = TextStyle(
                            fontSize = 30.sp
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(
                                top = screenWidth * 0.03f,
                                bottom = screenWidth * 0.03f
                            )
                            .align(Alignment.CenterVertically)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.mypage_arrow),
                    contentDescription = "mypagearrow",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable { /* Handle Click Here */ }
                )
            }
            //--------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(start = screenWidth * 0.025f, end = screenWidth * 0.025f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    // 이미지와 텍스트 사이에 간격을 주기 위해 spacedBy 사용
                    horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.01f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.version_history),
                        contentDescription = "matchingimage",
                        // menu icon과 menu 사이에 약 2% 크기의 padding 설정
                        Modifier
                            .padding(end = screenWidth * 0.03f)
                            .fillMaxHeight()
                    )
                    Text(
                        text = "버전 정보",
                        style = TextStyle(
                            fontSize = 30.sp
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(
                                top = screenWidth * 0.02f,
                                bottom = screenWidth * 0.02f
                            )
                            .align(Alignment.CenterVertically)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.mypage_arrow),
                    contentDescription = "mypagearrow",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable { /* Handle Click Here */ }
                )
            }
            //-------
        }
    }
}


@Composable
//overload
fun PhotoMateBottomBar(
    navController: NavController, modifier: Modifier
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        // PhotoMateBottomBar의 구현 부분
        val screens = listOf(Screen.Home, Screen.Notification, Screen.Profile)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavigationBar(
            containerColor = Color.White
        ) {
            screens.forEach { screen ->
                val selected = screen.route == currentRoute

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.DarkGray,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.DarkGray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.White
                    ),
                    label = {
                        Text(
                            text = screen.name,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = "${screen.name} Icon"
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onNavigateToModification = {}
    )
}
