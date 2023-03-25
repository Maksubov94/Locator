package com.maksubov.locator

import androidx.room.Room
import com.maksubov.locator.data.data_base.LocationDataBase
import com.maksubov.locator.data.repository.LocationRepository
import com.maksubov.locator.ui.StatisticViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            LocationDataBase::class.java,
            "MyDb"
        ).fallbackToDestructiveMigration().build()
    }


    viewModel {
        StatisticViewModel(get())
    }


    single {
        get<LocationDataBase>().getLocationDao()
    }


    single {
        LocationRepository(get())
    }


}