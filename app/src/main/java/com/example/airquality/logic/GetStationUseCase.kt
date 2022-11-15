package com.example.airquality.logic

import com.example.airquality.entity.AQStation
import com.example.airquality.repository.LocalStationsRepository
import com.example.airquality.repository.RemoteStationRepository
import javax.inject.Inject

class GetStationUseCase @Inject constructor(
    private val remoteStationRepository: RemoteStationRepository,
    private val localStationsRepository: LocalStationsRepository
)
{

    suspend fun execute() : List<AQStation>{
        return localStationsRepository.getAll()
    }
}