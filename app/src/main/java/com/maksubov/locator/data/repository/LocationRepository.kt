package com.maksubov.locator.data.repository

import com.maksubov.locator.data.dao.LocationDao
import com.maksubov.locator.entity.LocationEntity

class LocationRepository(
    private val dao: LocationDao
) {

    suspend fun addNewLocation(location: LocationEntity) {
        dao.insertLocation(location)
    }
}