package com.example.airquality.data

import com.example.airquality.airly.AirlyMapper
import com.example.airquality.airly.AirlyService
import com.example.airquality.entity.AQStation
import com.example.airquality.logic.RemoteStationRepository
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import javax.inject.Inject

class AirlyStationDataSource @Inject constructor(private val airlyService : AirlyService) : RemoteStationRepository {

    override suspend fun getAll(): List<AQStation> = withContext(Dispatchers.IO) {
        val installations = airlyService.getInstallations()
        return@withContext installations.map { AirlyMapper().mapInstallation(it) }
    }
}

