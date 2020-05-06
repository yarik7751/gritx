package com.solbegsoft.gritx.di.signin

import com.solbegsoft.gritx.di.domain.DomainProvider
import com.solbegsoft.gritx.di.navigation.NavigationProvider
import com.solbegsoft.gritx.di.tools.ToolsProvider
import com.solbegsoft.gritx.tools.exceptions.InitComponentException
import com.solbegsoft.gritx.view.signin.SignInFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [SignInModule::class],
    dependencies = [ToolsProvider::class, NavigationProvider::class, DomainProvider::class]
)
@Singleton
interface SignInComponent {

    fun inject(where: SignInFragment)

    companion object {

        private var component: SignInComponent? = null

        fun init(toolsProvider: ToolsProvider, navigationProvider: NavigationProvider, domainProvider: DomainProvider) {
            component = DaggerSignInComponent.builder()
                .navigationProvider(navigationProvider)
                .toolsProvider(toolsProvider)
                .domainProvider(domainProvider)
                .build()
        }

        fun get() = component ?: throw InitComponentException()
    }
}