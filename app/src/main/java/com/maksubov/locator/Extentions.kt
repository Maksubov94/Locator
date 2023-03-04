package com.maksubov.locator

import android.location.Location
import com.maksubov.locator.entity.LocationEntity
import java.util.UUID

fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        UUID.randomUUID().toString(),
        this.latitude,
        this.longitude,
        System.currentTimeMillis()
    )
}