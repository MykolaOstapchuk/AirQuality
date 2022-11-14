package com.example.airquality.logic

import com.example.airquality.entity.AQStation
import javax.inject.Inject

class GetStationUseCase @Inject constructor(
    private val remoteStationRepository: RemoteStationRepository
) {

    suspend fun execute() : List<AQStation>{
        return remoteStationRepository.getAll()
    }
}