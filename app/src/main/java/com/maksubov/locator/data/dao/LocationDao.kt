package com.maksubov.locator.data.dao

import androidx.room.*
import com.maksubov.locator.entity.LocationEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM location WHERE timeStamp >=:targetTime ORDER BY timeStamp ASC")
    fun getAllLocationsByTime(targetTime: Long): Flow<List<LocationEntity>>

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Delete
    suspend fun deleteLocation(location: LocationEntity)



}