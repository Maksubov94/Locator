package com.maksubov.locator.data.dao

import androidx.room.*
import com.maksubov.locator.entity.LocationEntity


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM location")
    fun getAllLocations(): List<LocationEntity>

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Delete
    suspend fun deleteLocation(location: LocationEntity)



}