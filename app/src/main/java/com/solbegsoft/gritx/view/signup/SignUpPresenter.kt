package com.solbegsoft.gritx.view.signup

import com.solbegsoft.gritx.core.repositories.ISignUpRepository
import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.extensions.emptyString
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider
import com.solbegsoft.gritx.view.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    router: Router,
    rxSchedulersProvider: IRxSchedulersProvider,
    resourceProvider: IResourceProvider,
    private val signUpRepository: ISignUpRepository
): BasePresenter<ISignUpView>(router, rxSchedulersProvider, resourceProvider) {

    var firstName = emptyString()
    var lastName = emptyString()
    var email = emptyString()
    var password = emptyString()

    fun submit() {
        signUpRepository.signUp(email, password, firstName, lastName)
            .subscribeOn(rxSchedulersProvider.io())
            .observeOn(rxSchedulersProvider.androidThread())
            .subscribe({
                it.hashCode()
            }, {
                onThrowable(it)
            }).connect()
    }
}