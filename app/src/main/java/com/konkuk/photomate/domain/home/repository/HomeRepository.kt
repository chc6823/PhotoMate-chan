package com.konkuk.photomate.domain.home.repository

interface HomeRepository {
    suspend fun fetchCurrentLocation(): Result<Boolean>
}
