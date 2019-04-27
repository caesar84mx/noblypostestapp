package com.caesar_84.noblypostestapp.commons.utils

import android.util.Log

object LogHelper {
    fun logToConsole(message: String?) {
        Log.d(Constants.Configuration.APPLICATION_TAG, message)
    }

    fun logToConsole(data: List<*>) {
        logToConsole("Logging data:")
        logToConsole("Start:")

        data.map {
            logToConsole(it.toString())
        }

        logToConsole("End.")
    }

    fun logToConsole(t: Throwable) {
        logToConsole(t.message)
        logToConsole("Start:")

        t.stackTrace.map {
            val line = it.lineNumber
            val className = it.className
            val method = it.methodName

            logToConsole("   -> In $className, method $method, line $line")
        }

        logToConsole("End.")
    }
}