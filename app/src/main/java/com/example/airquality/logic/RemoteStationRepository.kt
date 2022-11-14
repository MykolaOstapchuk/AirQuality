package com.example.airquality.logic

import com.example.airquality.entity.AQStation

interface RemoteStationRepository {
    suspend fun getAll() : List<AQStation>
}