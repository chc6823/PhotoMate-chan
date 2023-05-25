package com.konkuk.photomate.presentation.screens.mypage

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.konkuk.photomate.R
import com.konkuk.photomate.presentation.components.PhotoMateBottomBar
import com.konkuk.photomate.presentation.screens.mypage.ui.theme.PhotoMateTheme

class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MyPageScreen()
                }
            }
        }
    }
}
@Composable
fun MyPageScreen(){
    val navController = rememberNavController()
    val hoonieimage: Painter = painterResource(id = R.drawable.hoonie)
    val profilechangebutton: Painter = painterResource(id = R.drawable.profilechangebutton)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color =  Color.White
    ){
        Column(

        ) {
            Text(
                text = "내 정보",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            //My profile
            Row(
                modifier = Modifier.padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = hoonieimage,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "후니쓰",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .size(48.dp)
                        .clickable { /* Handle click */ }
                        .background(Color.LightGray)
                ) {
                    // Box content here
                    Image(
                        painter = profilechangebutton,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
            //매칭내역,모아보기,버전정보
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){

            }
            Row(){

            }
            Row(){

            }
            Spacer(modifier = Modifier.height(300.dp))
            PhotoMateBottomBar(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    PhotoMateTheme {
        MyPageScreen()
    }
}