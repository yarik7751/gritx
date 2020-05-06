package com.solbegsoft.gritx.di.signup

import com.solbegsoft.gritx.core.repositories.ISignUpRepository
import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider
import com.solbegsoft.gritx.view.signup.SignUpPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class SignUpModule {

    @Provides
    @Singleton
    fun provideSignUpPresenter(
        router: Router,
        rxSchedulersProvider: IRxSchedulersProvider,
        resourceProvider: IResourceProvider,
        signUpRepository: ISignUpRepository
    ): SignUpPresenter = SignUpPresenter(router, rxSchedulersProvider, resourceProvider, signUpRepository)
}