package com.dex.movieapp.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Executor that runs a thread at background
 */

class DiskIOThreadExecutor : Executor {

    private val diskIO = Executors.newSingleThreadExecutor()

    override fun execute(p0: Runnable?) {

     p0?.let { diskIO.execute(p0) }

    }

}
