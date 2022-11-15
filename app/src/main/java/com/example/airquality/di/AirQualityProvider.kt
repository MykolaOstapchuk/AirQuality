package com.example.airquality.di

import android.content.Context
import androidx.room.Room
import com.example.airquality.airly.AirlyEndpoint
import com.example.airquality.airly.AirlyService
import com.example.airquality.data.AirlyStationDataSource
import com.example.airquality.data.local.InMemoryStationsRepository
import com.example.airquality.data.local.db.AppDatabase
import com.example.airquality.data.local.db.DatabaseStationsRepository
import com.example.airquality.repository.LocalStationsRepository
import com.example.airquality.repository.RemoteStationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AirQualityProvider {

    @Provides
    @Singleton
    fun provideLocalStationsRepository(@ApplicationContext appContext:Context): LocalStationsRepository {
        val database = Room.databaseBuilder(appContext, AppDatabase::class.java,"AirQualityDb").build()
        return DatabaseStationsRepository(database)
    }

    @Provides
    @Singleton
    fun provideRemoteStationsRepository(airlyService: AirlyService): RemoteStationRepository {
        return AirlyStationDataSource(airlyService)
    }

    @Provides
    @Singleton
    fun provideAirlyAuthOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(AirlyAuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AirlyEndpoint.HOST)
            .build()
    }

    @Provides
    @Singleton
    fun provideAirlyService(retrofit: Retrofit): AirlyService {
        return retrofit.create(AirlyService::class.java)
    }

}

class AirlyAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("apikey","wcYpR4C4OQj318U41EQJp2IDAXd4iwnB")
        return chain.proceed(requestBuilder.build())
    }
}