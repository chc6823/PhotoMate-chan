package com.konkuk.photomate.data.home.repository

import com.konkuk.photomate.data.home.remote.RemoteHomeDataSource
import com.konkuk.photomate.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteHomeDataSource: RemoteHomeDataSource
) : HomeRepository {
    override suspend fun fetchCurrentLocation(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}
