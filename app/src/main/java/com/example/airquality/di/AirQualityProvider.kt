package com.example.airquality.di

import com.example.airquality.logic.FakeRemoteStationRepository
import com.example.airquality.logic.RemoteStationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AirQualityProvider {

    @Provides
    @Singleton
    fun provideRemoteStationRepository() : RemoteStationRepository {
        return FakeRemoteStationRepository()
    }

}