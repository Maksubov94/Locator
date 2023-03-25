package com.maksubov.locator.ui

import androidx.lifecycle.ViewModel
import com.maksubov.locator.data.repository.LocationRepository
import java.util.concurrent.TimeUnit

class StatisticViewModel(
    private val repo: LocationRepository
): ViewModel() {

    val dataFlow = repo.getDataByTime(
        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)
    )

}