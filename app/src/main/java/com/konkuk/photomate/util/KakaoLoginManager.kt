package com.konkuk.photomate.util

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class KakaoLoginManager @Inject constructor(
    @ActivityContext private val context: Context
) {
    private lateinit var kakaoLoginState: KakaoLoginState
    private lateinit var kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit

    fun startKakaoLogin(
        updateSocialToken: (String) -> Unit
    ) {

        kakaoLoginState = getKaKaoLoginState()
        kakaoLoginCallback = getLoginCallback(updateSocialToken)

        when (kakaoLoginState) {
            // 그냥 계속 카카오 계정으로 로그인 하도록.. 영상찍어야 하니
            KakaoLoginState.KAKAO_TALK_LOGIN -> onKakaoAccountLogin()
            KakaoLoginState.KAKAO_ACCOUNT_LOGIN -> onKakaoAccountLogin()
        }
    }

    private fun getKaKaoLoginState(): KakaoLoginState =
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context))
            KakaoLoginState.KAKAO_TALK_LOGIN
        else KakaoLoginState.KAKAO_ACCOUNT_LOGIN

    private fun getLoginCallback(updateSocialToken: (String) -> Unit): (OAuthToken?, Throwable?) -> Unit {
        return { token, error ->
            if (error != null)
                Timber.tag(KAKAO_LOGIN).d("${error.message} 카카오 계정으로 로그인 실패")
            else if (token != null)
                updateSocialToken(token.accessToken)
        }
    }

    private fun onKakaoAccountLogin() {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoLoginCallback)
    }

    companion object {
        const val KAKAO_LOGIN = "kako_login"
    }
}

enum class KakaoLoginState {
    KAKAO_TALK_LOGIN, KAKAO_ACCOUNT_LOGIN
}