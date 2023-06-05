package com.konkuk.photomate.presentation.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

//    fun getCurrentLocation(location: Location) {
//        val currentLocation = homeUseCases.fetchCurrentLocationUseCase(location)
//    }

//    fun onCurrentLocationClick() {
//        _state.update {
//            it.copy(
//                properties = _state.value.properties.copy(
//                    locationTrackingMode =
//                    if (_state.value.properties.locationTrackingMode == LocationTrackingMode.NoFollow) {
//                        LocationTrackingMode.Follow
//                    } else {
//                        LocationTrackingMode.NoFollow
//                    }
//                )
//            )
//        }
//    }
}
