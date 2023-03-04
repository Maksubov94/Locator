package com.maksubov.locator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val timeStamp: Long
)