package com.dex.movieapp.utils


import java.util.concurrent.Executor
import java.util.concurrent.Executors

const val THREAD_COUNT = 3

/**
 * Global executor pools for the whole application.
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */

open class AppExecutors(val diskIO: Executor = DiskIOThreadExecutor(),
                        val networkIO: Executor = Executors.newFixedThreadPool(THREAD_COUNT),
                        val mainThread: Executor = MainThreadExecutor())

