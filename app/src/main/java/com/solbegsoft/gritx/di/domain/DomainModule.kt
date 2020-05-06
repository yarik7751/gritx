package com.solbegsoft.gritx.di.domain

import com.solbegsoft.gritx.core.repositories.ISignInRepository
import com.solbegsoft.gritx.core.repositories.ISignUpRepository
import com.solbegsoft.gritx.core.repositories.SignInRepository
import com.solbegsoft.gritx.core.repositories.SignUpRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Singleton
    fun bindsSignInRepository(impl: SignInRepository): ISignInRepository

    @Binds
    @Singleton
    fun bindsSignUpRepository(impl: SignUpRepository): ISignUpRepository
}