package com.maksubov.locator

import android.content.Context
import java.io.File
import java.io.FileInputStream

class FileUtil(
   val context: Context
) {
    private val storageName = "storage"

    private val logName = "log.txt"

    val atomicLimit = 1024 * 1024 * 5

    private val storageFile = File(context.applicationInfo.dataDir + File.separator + storageName)

    private val logFile = File(context.applicationInfo.dataDir + File.separator + logName)


    fun checkExist(): Boolean{
        return storageFile.isFile
    }


    fun getStorageLen() = storageFile.length()


    fun createStorage(){
        storageFile.createNewFile()
    }


    fun deleteStorage(){
        storageFile.delete()
    }

    suspend fun writeData(data: ByteArray, append: Boolean){
        if(append){
          storageFile.appendBytes(data)
        } else storageFile.writeBytes(data)
    }

    suspend fun readBytesAtomic(): ByteArray = storageFile.readBytes()

    suspend fun readBytesNonAtomic(
        onPartRead: (ByteArray)-> (Unit)
    ){
        FileInputStream(storageFile).use { fis->
            while (fis.available() > 0) {
                val size = if (fis.available() < atomicLimit) fis.available() else atomicLimit
                onPartRead.invoke(
                    ByteArray(size).also { fis.read(it) }
                )
            }
        }
    }


    fun getDirContent(): Array<File>?{
        return File(context.applicationInfo.dataDir).listFiles()
    }


    suspend fun readLogFile(
        lineHandler: (String) -> (Unit)
    ){
        logFile.bufferedReader().use {
            while (it.ready()) {
                lineHandler.invoke(
                    it.readLine()
                )
            }
        }
    }
}