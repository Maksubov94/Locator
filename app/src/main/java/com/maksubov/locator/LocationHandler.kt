package com.maksubov.locator

import android.content.ContentValues.TAG
import android.util.Log
import com.google.maps.android.SphericalUtil
import com.maksubov.locator.entity.LocationEntity
import kotlin.math.log

class LocationHandler {


    fun handleData(data: List<LocationEntity>): HandledStatisticData {
        return HandledStatisticData(
            calculateDistanceByList(data),
            calculateTime(data)
        )
    }

    private fun calculateDistanceByList(data: List<LocationEntity>): Float {
        //По пришедшему списку рассчитать пройденое(проеханное) расстояние

        data

        var  distance: Double = SphericalUtil.computeDistanceBetween();


    }

    private fun calculateTime(data: List<LocationEntity>): String {


        return "время которое потребовалось для прохождения"

    }


}