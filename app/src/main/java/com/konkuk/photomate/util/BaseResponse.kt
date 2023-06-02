package com.konkuk.photomate.util

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T?
)
