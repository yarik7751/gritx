package com.solbegsoft.gritx.di.tools

import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider

interface ToolsProvider {

    fun provideRxSchedulersProvider(): IRxSchedulersProvider

    fun provideResourceProvider(): IResourceProvider
}