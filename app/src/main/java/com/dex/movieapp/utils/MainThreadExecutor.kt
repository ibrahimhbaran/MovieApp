package com.dex.movieapp.utils

import android.os.Handler
import android.os.Looper.getMainLooper
import java.util.concurrent.Executor


class MainThreadExecutor : Executor {

    private val mainThreadHandler = Handler(getMainLooper())

    override fun execute(p0: Runnable?) {

        p0?.let { mainThreadHandler.post(p0) }

    }
}