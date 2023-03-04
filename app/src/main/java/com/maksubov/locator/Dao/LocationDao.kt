package com.maksubov.locator.Dao

import android.location.Location
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@androidx.room.Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(location: Location)

    @Query("SELECT*FROM Location")
    fun getAllDate(): MutableList<Location>

    @Update
    suspend fun updateLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)



}