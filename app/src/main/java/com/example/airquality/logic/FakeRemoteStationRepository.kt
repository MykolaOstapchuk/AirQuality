package com.example.airquality.logic

import com.example.airquality.entity.AQStation
import com.example.airquality.repository.RemoteStationRepository

class FakeRemoteStationRepository : RemoteStationRepository {
    override suspend fun getAll(): List<AQStation> {
        return emptyList()
    }
}