package com.maksubov.locator

import androidx.room.Room
import com.maksubov.locator.data.data_base.LocationDataBase
import com.maksubov.locator.data.repository.LocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            LocationDataBase::class.java,
            "MyDb"
        ).fallbackToDestructiveMigration().build()
    }


    single {
        get<LocationDataBase>().getLocationDao()
    }


    single {
        LocationRepository(get())
    }


}