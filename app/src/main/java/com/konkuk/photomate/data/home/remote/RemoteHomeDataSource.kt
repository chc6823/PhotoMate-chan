package com.konkuk.photomate.data.home.remote

import com.konkuk.photomate.util.BaseResponse
import com.konkuk.photomate.util.NetworkState

interface RemoteHomeDataSource {
    suspend fun getCurrentAddress(): NetworkState<BaseResponse<Unit>>
}
