package com.example.airquality.data.local.db

import androidx.room.*
import com.example.airquality.entity.AQStation
import com.example.airquality.repository.LocalStationsRepository
import javax.inject.Inject

class DatabaseStationsRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : LocalStationsRepository {
    override suspend fun getAll(): List<AQStation> {
        val stationEntity = appDatabase.stationDAO().getAll()
        return stationEntity.map { AQStation(it.uuid,it.name,it.city,it.sponsor,it.sponsorImage) }
    }

    override suspend fun save(stations: List<AQStation>) {
        appDatabase.stationDAO().insert(stations.map { StationEntity(it.id,it.name,it.city,it.sponsor,"") })
    }
}

@Entity
data class StationEntity(
    @PrimaryKey
    val uuid: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "sponsor")
    val sponsor: String,
    @ColumnInfo(name = "sponsor_image")
    val sponsorImage: String
)

@Dao
interface StationsDao{
    @Query("SELECT * FROM stationentity")
    suspend fun getAll(): List<StationEntity>

    @Insert
    suspend fun insert(stations: List<StationEntity>)
}

@Database(entities = [StationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDAO() : StationsDao
}