package com.example.airquality.repository

import com.example.airquality.entity.AQStation

interface RemoteStationRepository {
    suspend fun getAll() : List<AQStation>
}