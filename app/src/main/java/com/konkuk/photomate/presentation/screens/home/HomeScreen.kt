@file:OptIn(ExperimentalNaverMapApi::class)

package com.konkuk.photomate.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.konkuk.photomate.util.components.PhotoMateProgressBar
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState
) {
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = state.cameraPosition
    }
    var isProgressBarVisible by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            locationSource = rememberFusedLocationSource(),
            properties = state.properties,
            uiSettings = state.uiSettings,
            onMapLoaded = {
                isProgressBarVisible = false
            }
        )

        if (isProgressBarVisible) {
            PhotoMateProgressBar()
        }
    }
}
