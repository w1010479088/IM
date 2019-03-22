package com.example.im

import android.os.Handler
import android.os.Looper

object MainHandler {
    private val mHandler: Handler = Handler(Looper.getMainLooper())

    fun main(runnable: Runnable) {
        mHandler.post(runnable)
    }
}
