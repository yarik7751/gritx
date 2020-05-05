package com.solbegsoft.gritx.tools.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject

class RxSchedulersProvider @Inject constructor() :
    IRxSchedulersProvider {

    private var backgroundExecutor = Executors.newCachedThreadPool()
    private var BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor)
    private var internetExecutor = Executors.newCachedThreadPool()
    private var INTERNET_SCHEDULERS = Schedulers.from(internetExecutor)

    override fun runOnBackground(): Scheduler {
        return BACKGROUND_SCHEDULERS
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun compute(): Scheduler {
        return Schedulers.computation()
    }

    override fun androidThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun internet(): Scheduler {
        return INTERNET_SCHEDULERS
    }
}

interface IRxSchedulersProvider {

    fun runOnBackground(): Scheduler

    fun io(): Scheduler

    fun compute(): Scheduler

    fun androidThread(): Scheduler

    fun internet(): Scheduler
}