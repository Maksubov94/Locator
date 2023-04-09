package com.maksubov.locator

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.maksubov.locator.entity.LocationEntity
import java.text.DateFormat
import java.util.*

class LocationHandler {


    fun handleData(data: List<LocationEntity>): HandledStatisticData {
        return HandledStatisticData(
            calculateDistanceByList(data),
            calculateTime(data)
        )
    }

    private fun calculateDistanceByList(data: List<LocationEntity>): Double {
        var previous = data.first()
        var result = 0.0
        for (i in 1 until data.size) {
            val next = data[i]
            result += SphericalUtil.computeDistanceBetween(
                LatLng(previous.latitude, previous.longitude),
                LatLng(next.latitude, next.longitude),
            )
            previous = next
        }
        return result
    }

    private fun calculateTime(data: List<LocationEntity>): String {
        val timeList = data.map { it.timeStamp }
        val min = timeList.min()
        val max = timeList.max()
        return DateFormat.getDateInstance().format(Date(max - min))
    }


}