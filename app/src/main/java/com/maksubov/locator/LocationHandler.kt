package com.maksubov.locator

import com.maksubov.locator.entity.LocationEntity

class LocationHandler {


    fun handleData(data: List<LocationEntity>): HandledStatisticData{
        return HandledStatisticData(
            calculateDistanceByList(data),
            calculateTime(data)
        )
    }

    private fun calculateDistanceByList(data: List<LocationEntity>): Float{
        //По пришедшему списку рассчитать пройденое(проеханное) расстояние
    }

    private fun calculateTime(data: List<LocationEntity>): String{

    }




}