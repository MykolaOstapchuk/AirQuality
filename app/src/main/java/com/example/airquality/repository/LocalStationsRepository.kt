package com.example.airquality.repository

import com.example.airquality.entity.AQStation

interface LocalStationsRepository {
    suspend fun getAll(): List<AQStation>
    suspend fun save(stations: List<AQStation>)
}