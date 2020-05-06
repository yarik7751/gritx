package com.solbegsoft.gritx.di.remote

import com.solbegsoft.gritx.core.remote.IGritxApiService
import com.solbegsoft.gritx.tools.exceptions.InitComponentException
import dagger.Component
import javax.inject.Singleton

@Component(modules = [GritxApiServiceModule::class])
@Singleton
interface GritxApiServiceComponent: GritxApiServiceProvider {

    companion object {

        private var component: GritxApiServiceComponent? = null

        fun init() {
            component = DaggerGritxApiServiceComponent.builder().build()
        }

        fun get() = component ?: throw InitComponentException()
    }
}

interface GritxApiServiceProvider {
    fun provideGritxApiService(): IGritxApiService
}