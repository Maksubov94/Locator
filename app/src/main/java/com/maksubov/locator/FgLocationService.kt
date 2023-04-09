package com.maksubov.locator

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.maksubov.locator.data.repository.LocationRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject


class FgLocationService: Service(){

    companion object {
        private const val NOTIFICATION_SERVICE_ID = 77
        private const val NOTIFICATION_CHANNEL = "LOC_SERVICE_NF_CH"
        private const val NOTIFICATION_CHANNEL_NAME = "LOC service notification"
    }

    private val serviceScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    private val locRepo: LocationRepository by inject()


    override fun onBind(intent: Intent?): IBinder? = null


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        subscribeLocation()
    }

    private fun subscribeLocation() {
        LocationSource.startListening(this)
        serviceScope.launch {
            LocationSource.locationFlow.collectLatest {
               it.handleNewLocation(this@FgLocationService){
                   serviceScope.launch {
                       locRepo.addNewLocation(it)
                   }
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_SERVICE_ID, createNotification())
        return START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {

        return super.stopService(name)
    }
//    fun stopService(context: Context) {
//        val stopIntent = Intent(context, FgLocationService::class.java)
//        context.stopService(stopIntent)}


    private fun createNotification(): Notification {
        return NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setContentTitle("")
            .setSmallIcon(R.drawable.baseline_add_reaction_24)
            .setContentText("")
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .build()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationSource.stopListening()
    }

}