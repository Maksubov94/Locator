package com.maksubov.locator

import android.content.Context
import android.content.ContextWrapper
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Logger(context: Context) {

    private val logFile =  File("${ContextWrapper(context).applicationInfo.dataDir}${File.separator}applog.txt")

    fun log(info: String){
        val timeStamp = System.currentTimeMillis()
        if(!logFile.isFile){
            logFile.createNewFile()
            //write config of device
        }
        val date = SimpleDateFormat("mm-HH-dd-MM-yy", Locale.getDefault()).format(Date(timeStamp))
        logFile.appendText("$date : $info\n\n")
    }


    fun clearLog(){}

    fun uploadLogToSdCard(){}

    fun getLogAsByteArray(){}

}