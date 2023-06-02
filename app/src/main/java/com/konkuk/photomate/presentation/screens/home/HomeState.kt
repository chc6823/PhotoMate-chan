package com.konkuk.photomate.presentation.screens.home

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings

data class HomeState(
    val properties: MapProperties = MapProperties(
        locationTrackingMode = LocationTrackingMode.NoFollow,
        isNightModeEnabled = true,
        isIndoorEnabled = true
    ),
    val uiSettings: MapUiSettings = MapUiSettings(
        isLocationButtonEnabled = false,
        isCompassEnabled = false,
        isIndoorLevelPickerEnabled = true,
        isZoomControlEnabled = false,
        rotateGesturesFriction = 0f
    ),
    val cameraPosition: CameraPosition = CameraPosition(LatLng(DefaultLat, DefaultLng), DefaultZoomLevel),
    val isBottomSheetShown: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

private const val DefaultLat = 37.540104
private const val DefaultLng = 127.070686
private const val DefaultZoomLevel = 14.5
