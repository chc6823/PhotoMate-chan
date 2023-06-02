package com.konkuk.photomate.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.map.compose.LocationTrackingMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun onFloatingButtonClicked() {
        viewModelScope.launch {

        }
    }

    fun onCurrentLocationClick() {
        _state.update {
            it.copy(
                properties = _state.value.properties.copy(
                    locationTrackingMode =
                    if (_state.value.properties.locationTrackingMode == LocationTrackingMode.NoFollow) {
                        LocationTrackingMode.Follow
                    } else {
                        LocationTrackingMode.NoFollow
                    }
                )
            )
        }
    }
}