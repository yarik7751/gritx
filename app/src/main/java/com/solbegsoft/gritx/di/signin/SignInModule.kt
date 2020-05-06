package com.solbegsoft.gritx.di.signin

import com.solbegsoft.gritx.core.repositories.ISignInRepository
import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider
import com.solbegsoft.gritx.view.signin.SignInPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class SignInModule {

    @Provides
    @Singleton
    fun provideSignInPresenter(
        router: Router,
        rxSchedulersProvider: IRxSchedulersProvider,
        resourceProvider: IResourceProvider,
        signInRepository: ISignInRepository
    ): SignInPresenter = SignInPresenter(router, rxSchedulersProvider, resourceProvider, signInRepository)
}