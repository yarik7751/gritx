package com.solbegsoft.gritx.di.navigation

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun getCicerone() = Cicerone.create(Router())

    @Provides
    @Singleton
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun getNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder
}