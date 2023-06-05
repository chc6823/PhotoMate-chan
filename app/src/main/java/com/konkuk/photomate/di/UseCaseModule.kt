package com.konkuk.photomate.di

import android.content.Context
import com.konkuk.photomate.domain.home.usecase.FetchCurrentLocationUseCase
import com.konkuk.photomate.domain.home.usecase.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun providesHomeUseCases(@ApplicationContext context: Context): HomeUseCases {
        return HomeUseCases(
            fetchCurrentLocationUseCase = FetchCurrentLocationUseCase(context)
        )
    }
}
