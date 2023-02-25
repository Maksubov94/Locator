package com.maksubov.locator

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


class FgLocationService: Service(){

    companion object {
        private const val NOTIFICATION_SERVICE_ID = 77
        private const val NOTIFICATION_CHANNEL = "LOC_SERVICE_NF_CH"
        private const val NOTIFICATION_CHANNEL_NAME = "LOC service notification"
    }

    private val serviceScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)


    override fun onBind(intent: Intent?): IBinder? = null


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        subscribeLocation()
    }

    private fun subscribeLocation() {
        serviceScope.launch {
            LocationSource.startListening(this@FgLocationService)
            LocationSource.locationFlow.collectLatest {

            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_SERVICE_ID, createNotification())
        return START_STICKY
    }

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


}