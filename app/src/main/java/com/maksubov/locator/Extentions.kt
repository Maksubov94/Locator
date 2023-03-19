package com.maksubov.locator

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.maksubov.locator.entity.LocationEntity
import java.util.*

fun Location.handleNewLocation(
    context: Context,
    onDone: (LocationEntity) -> (Unit)
) {

    val geocoder = Geocoder(context, Locale.getDefault())

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        try {
            geocoder.getFromLocation(latitude, longitude, 1) {
                onDone.invoke(
                    LocationEntity(
                        UUID.randomUUID().toString(),
                        this.latitude,
                        this.longitude,
                        System.currentTimeMillis(),
                        it.firstOrNull()?.toString()
                    )
                )
            }
        } catch (e: Exception){
            e.printStackTrace()
            onDone.invoke(
                LocationEntity(
                    UUID.randomUUID().toString(),
                    this.latitude,
                    this.longitude,
                    System.currentTimeMillis(),
                    null
                )
            )
        }
    } else {
        try {
            val result = geocoder.getFromLocation(latitude, longitude, 1)
            onDone.invoke(
                LocationEntity(
                    UUID.randomUUID().toString(),
                    this.latitude,
                    this.longitude,
                    System.currentTimeMillis(),
                    result?.firstOrNull()?.toString()
                )
            )
        } catch (e: Exception){
            e.printStackTrace()
            onDone.invoke(
                LocationEntity(
                    UUID.randomUUID().toString(),
                    this.latitude,
                    this.longitude,
                    System.currentTimeMillis(),
                    null
                )
            )
        }
    }
}