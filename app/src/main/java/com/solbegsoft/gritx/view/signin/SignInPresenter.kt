package com.solbegsoft.gritx.view.signin

import com.solbegsoft.gritx.core.repositories.ISignInRepository
import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.extensions.emptyString
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider
import com.solbegsoft.gritx.view.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SignInPresenter @Inject constructor(
    router: Router,
    rxSchedulersProvider: IRxSchedulersProvider,
    resourceProvider: IResourceProvider,
    private val signInRepository: ISignInRepository
): BasePresenter<ISignInView>(router, rxSchedulersProvider, resourceProvider) {

    var email = emptyString()
    var password = emptyString()

    fun submit() {
        signInRepository.signIn(email, password)
            .subscribeOn(rxSchedulersProvider.io())
            .observeOn(rxSchedulersProvider.androidThread())
            .subscribe({
                it.hashCode()
            }, {
                onThrowable(it)
            }).connect()
    }
}