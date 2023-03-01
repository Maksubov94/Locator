package com.maksubov.locator.data_base

import android.location.Location
import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksubov.locator.Dao.LocationDao

@Database(version = 1, entities = [Location::class])
abstract class LocationDataBase: RoomDatabase() {

    abstract fun locationDataBase(): LocationDao






}