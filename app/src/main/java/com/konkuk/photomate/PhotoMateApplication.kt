package com.konkuk.photomate

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import com.konkuk.photomate.util.PhotoMateDebugTree
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp
import com.konkuk.photomate.BuildConfig.KAKAO_NATIVE_APP_KEY
import com.konkuk.photomate.BuildConfig.NAVER_CLIENT_ID
import timber.log.Timber

@HiltAndroidApp
class PhotoMateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKakaoSdk(this)
        initNaverMapSdk(this)
    }
}

private fun initTimber() {
    if (BuildConfig.DEBUG) {
        Timber.plant(PhotoMateDebugTree())
    }
}

private fun initKakaoSdk(context: Context) {
    KakaoSdk.init(context, KAKAO_NATIVE_APP_KEY)
}

private fun initNaverMapSdk(context: Context) {
    NaverMapSdk.getInstance(context).client =
        NaverMapSdk.NaverCloudPlatformClient(NAVER_CLIENT_ID)
}
