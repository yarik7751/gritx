package com.solbegsoft.gritx.di.domain

import com.solbegsoft.gritx.core.repositories.ISignInRepository
import com.solbegsoft.gritx.di.remote.GritxApiServiceProvider
import com.solbegsoft.gritx.tools.exceptions.InitComponentException
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [DomainModule::class],
    dependencies = [GritxApiServiceProvider::class]
)
@Singleton
interface DomainComponent: DomainProvider {

    companion object {

        private var component: DomainComponent? = null

        fun init(gritxApiServiceProvider: GritxApiServiceProvider) {
            component = DaggerDomainComponent.builder()
                .gritxApiServiceProvider(gritxApiServiceProvider)
                .build()
        }

        fun get() = component ?: throw InitComponentException()
    }
}

interface DomainProvider {

    fun provideSignInRepository(): ISignInRepository
}