package com.konkuk.photomate.presentation.screens.review

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import com.konkuk.photomate.R
import com.konkuk.photomate.presentation.screens.review.ui.theme.PhotoMateTheme

class ReviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun reviewscreen(){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val rating = remember { mutableStateOf(0f) } // initialize with your initial rating

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            //verticalArrangement = Arrangement.SpaceBetween
        ) {
            //cancel button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.07f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painterResource(R.drawable.x_button),
                    contentDescription = "X_button",
                    modifier = Modifier
                        .width(screenWidth * 0.07f)
                        .fillMaxHeight()
                )
            }
            //-------
            Text(
                text = "마블리님과의 사진촬영은 어떠셨나요?",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.07f),
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit((screenWidth * 0.06f).value, TextUnitType.Sp),
                textAlign = TextAlign.Center
            )
            //------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.mavley),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(screenWidth * 0.4f, screenHeight)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height((screenHeight * 0.03f)))
            //--------
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(screenHeight * 0.10f)
                    .align(Alignment.CenterHorizontally)
            ) {
                RatingBar(rating)
            }
            //---------
            Spacer(modifier = Modifier.height((screenHeight * 0.03f)))
            //---------
            Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
            //---------
            Spacer(modifier = Modifier.height((screenHeight * 0.2f)))
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(screenHeight * 0.2f)
                    .align(Alignment.CenterHorizontally)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.weight(1f)) // takes up remaining space

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.1f)
                        .background(colorResource(id = R.color.main_color))
                        .clickable { /* 클릭 행동 처리 */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "리뷰 남기기",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                        fontSize = TextUnit((screenHeight * 0.03f).value, TextUnitType.Sp)
                    )
                }
            }



        }
    }
}

@Composable
fun RatingBar(rating: MutableState<Float>) {
    AndroidView(
        //factory 블록 : view를 얻기 위해 최초로 실행되는 블록
        factory = { context ->
            SimpleRatingBar(context).apply {
                // apply some initial settings if needed
            }
        },
        //recompose될때마다 실행되는 블록
        update = { view ->
            view.rating = rating.value
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    PhotoMateTheme {
        reviewscreen()
    }
}