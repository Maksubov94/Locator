package com.maksubov.locator.ui

import com.maksubov.locator.FileUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FileCommander(
    val fileUtil: FileUtil
) {

    private val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    fun handleLog(){
        scope.launch {
            fileUtil.readLogFile { line->
                     dfgdfgdfg
            }
        }
    }

}