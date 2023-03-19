package com.maksubov.locator

import android.app.Application
import androidx.room.Room
import com.maksubov.locator.data.data_base.LocationDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LocatorApp: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LocatorApp)
            modules(module)
        }


    }
}