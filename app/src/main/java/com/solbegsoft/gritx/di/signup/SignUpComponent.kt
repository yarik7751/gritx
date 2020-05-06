package com.solbegsoft.gritx.di.signin

import com.solbegsoft.gritx.di.domain.DomainProvider
import com.solbegsoft.gritx.di.navigation.NavigationProvider
import com.solbegsoft.gritx.di.signup.SignUpModule
import com.solbegsoft.gritx.di.tools.ToolsProvider
import com.solbegsoft.gritx.tools.exceptions.InitComponentException
import com.solbegsoft.gritx.view.signup.SignUpFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [SignUpModule::class],
    dependencies = [ToolsProvider::class, NavigationProvider::class, DomainProvider::class]
)
@Singleton
interface SignUpComponent {

    fun inject(where: SignUpFragment)

    companion object {

        private var component: SignUpComponent? = null

        fun init(toolsProvider: ToolsProvider, navigationProvider: NavigationProvider, domainProvider: DomainProvider) {
            component = DaggerSignUpComponent.builder()
                .navigationProvider(navigationProvider)
                .toolsProvider(toolsProvider)
                .domainProvider(domainProvider)
                .build()
        }

        fun get() = component ?: throw InitComponentException()
    }
}