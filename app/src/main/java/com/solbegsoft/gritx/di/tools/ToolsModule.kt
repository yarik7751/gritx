package com.solbegsoft.gritx.di.tools

import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.android.ResourceProvider
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider
import com.solbegsoft.gritx.tools.rx.RxSchedulersProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ToolsModule {

    @Binds
    @Singleton
    fun bindsRxSchedulersProvider(impl: RxSchedulersProvider): IRxSchedulersProvider

    @Binds
    @Singleton
    fun bindsResourceProvider(impl: ResourceProvider): IResourceProvider
}