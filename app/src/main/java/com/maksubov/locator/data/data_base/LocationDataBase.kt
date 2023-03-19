package com.maksubov.locator.data.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksubov.locator.data.dao.LocationDao
import com.maksubov.locator.entity.LocationEntity

@Database(version = 2, entities = [LocationEntity::class])
abstract class LocationDataBase: RoomDatabase() {

    abstract fun getLocationDao(): LocationDao

}