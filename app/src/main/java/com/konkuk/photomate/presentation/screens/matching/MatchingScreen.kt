package com.konkuk.photomate.presentation.screens.matching

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.konkuk.photomate.R

@Composable
fun MatchingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    name: String,
    rating: Float,
    imageUrl: String = "http://117.16.142.55/wordpress/wp-content/uploads/2014/11/img_02.jpg",
    representativeImages: List<String> = emptyList()
) {
    val context = LocalContext.current
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.White)
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { navController.navigateUp() },
                    text = "거절하기",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(100.dp))
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = name,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "님이 매칭되었어요."
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "평점",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "별모양 아이콘",
                        tint = colorResource(id = R.color.main_color),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = rating.toString(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = colorResource(id = R.color.main_color)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        modifier = Modifier.size(150.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl).build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "프로필 사진"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "대표작품",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    representativeImages.forEach { representativeImage ->
                        Surface(
                            modifier = Modifier.size(80.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxSize(),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(representativeImage).build(),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "프로필 사진"
                                )
                            }
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.main_color))
                .clickable {
                    openCameraScreen(context) // error
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 25.dp),
                text = "카메라 준비하기",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
            )
        }
    }

    BackHandler {
        navController.navigateUp()
    }
}

fun openCameraScreen(
    context: Context
) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(intent)
}

@Preview
@Composable
fun MatchingScreenPreview() {
    MatchingScreen(
        navController = rememberNavController(),
        name = "김지인",
        rating = 2.3f,
        imageUrl = "http://117.16.142.55/wordpress/wp-content/uploads/2014/11/img_02.jpg",
    )
}
