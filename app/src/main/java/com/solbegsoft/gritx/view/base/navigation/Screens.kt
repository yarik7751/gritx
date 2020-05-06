package com.solbegsoft.gritx.view.base.navigation

import com.solbegsoft.gritx.view.signin.SignInFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens: SupportAppScreen() {

    object SignInScreen: Screens() {
        override fun getFragment() = SignInFragment.newInstance()
    }
}