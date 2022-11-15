package com.example.airquality.logic.usecase

import com.example.airquality.entity.AQStation
import com.example.airquality.logic.GetStationUseCase
import com.example.airquality.repository.LocalStationsRepository
import com.example.airquality.repository.RemoteStationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetStationUseCaseTest{

    lateinit var sut : GetStationUseCase
    lateinit var remote : MockRemoteStationsRepository
    lateinit var local : MockLocalStationsRepository

    @Before
    fun setUp(){
        local  = MockLocalStationsRepository()
        remote = MockRemoteStationsRepository()
        sut    = GetStationUseCase(remoteStationRepository = remote,localStationsRepository = local)
    }

    @Test
    fun init_DoesNotMakeAnyRemoteOrLocalCalls() {
        val sut = GetStationUseCase(remoteStationRepository = remote, localStationsRepository = local)
        assertEquals(false,remote.getAllCalled)
    }

    @Test
    fun executeMakesOneCallToLocal() = runBlocking {
        sut.execute()
        assertEquals(1,local.getAllCallsCount)
    }
 }

class MockLocalStationsRepository : LocalStationsRepository{
    val getAllCalled : Boolean
        get() = getAllCallsCount>0
    var getAllCallsCount : Int = 0

    val saveCalled : Boolean
        get() = saveCallsCount>0
    var saveCallsCount : Int = 0

    override suspend fun getAll(): List<AQStation> {
        getAllCallsCount+=1
        return listOf()
    }

    override suspend fun save(stations: List<AQStation>) {
        saveCallsCount+=1
    }
}

class MockRemoteStationsRepository: RemoteStationRepository {
    val getAllCalled : Boolean
        get() = getAllCallsCount>0
    var getAllCallsCount : Int = 0

    override suspend fun getAll(): List<AQStation> {
        getAllCallsCount+=1
        return listOf()
    }
}