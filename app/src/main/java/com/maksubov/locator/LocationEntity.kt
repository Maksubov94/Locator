package com.maksubov.locator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Location")
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    var id: String,

    @ColumnInfo(name = "Street")
    var street: String,

    @ColumnInfo(name = "City")
    var city: String,

    @ColumnInfo(name = "Date")
    var date: Date,

    @ColumnInfo(name = "Period")
    var time: Double,

)