package com.solbegsoft.gritx.di.navigation

import com.solbegsoft.gritx.tools.exceptions.InitComponentException
import com.solbegsoft.gritx.view.activity.MainActivity
import dagger.Component
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface NavigationComponent: NavigationProvider {

    fun inject(where: MainActivity)

    companion object {

        private var component: NavigationComponent? = null

        fun init() {
            component = DaggerNavigationComponent.builder().build()
        }

        fun get() = component ?: throw InitComponentException()
    }
}

interface NavigationProvider {

    fun provideRouter(): Router
}