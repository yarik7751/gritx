package com.solbegsoft.gritx

import android.app.Application
import com.solbegsoft.gritx.di.domain.DomainComponent
import com.solbegsoft.gritx.di.navigation.NavigationComponent
import com.solbegsoft.gritx.di.remote.GritxApiServiceComponent
import com.solbegsoft.gritx.di.signin.SignInComponent
import com.solbegsoft.gritx.di.tools.ToolsComponent

class GritxApp: Application() {

    override fun onCreate() {
        super.onCreate()

        GritxApiServiceComponent.init()
        val gritxApiServiceComponent = GritxApiServiceComponent.get()

        DomainComponent.init(gritxApiServiceComponent)
        val domainComponent = DomainComponent.get()

        ToolsComponent.init(applicationContext)
        val toolsComponent = ToolsComponent.get()

        NavigationComponent.init()
        val navigationComponent = NavigationComponent.get()

        SignInComponent.init(toolsComponent, navigationComponent, domainComponent)
    }
}