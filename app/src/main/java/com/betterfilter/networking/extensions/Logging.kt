package com.betterfilter.networking.extensions

import android.util.Log

fun Any.info(message: Any?, throwable: Throwable? = null) {
    Log.i(this.javaClass.simpleName, message.toString())
    throwable?.printStackTrace()
}

fun Any.debug(message: Any?, throwable: Throwable? = null) {
    Log.d(this.javaClass.simpleName, message.toString())
    throwable?.printStackTrace()
}