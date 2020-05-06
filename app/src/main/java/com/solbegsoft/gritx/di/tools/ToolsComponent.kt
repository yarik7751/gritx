package com.solbegsoft.gritx.di.tools

import android.content.Context
import com.solbegsoft.gritx.tools.exceptions.InitComponentException
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ToolsModule::class])
@Singleton
interface ToolsComponent: ToolsProvider {

    @Component.Builder
    interface Builder {

        fun build(): ToolsComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    companion object {

        private var component: ToolsComponent? = null

        fun init(context: Context) {
            component = DaggerToolsComponent.builder()
                .context(context)
                .build()
        }

        fun get() = component ?: throw InitComponentException()
    }
}