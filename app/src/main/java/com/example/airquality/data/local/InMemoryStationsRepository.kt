package com.example.airquality.data.local

import com.example.airquality.entity.AQStation
import com.example.airquality.repository.LocalStationsRepository

class InMemoryStationsRepository : LocalStationsRepository {
    override suspend fun getAll(): List<AQStation> {
        TODO("Not yet implemented")
    }

    override suspend fun save(stations: List<AQStation>) {
        TODO("Not yet implemented")
    }
}