package com.solbegsoft.gritx.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.solbegsoft.gritx.R
import com.solbegsoft.gritx.di.navigation.NavigationComponent
import com.solbegsoft.gritx.view.base.IBackPressed
import com.solbegsoft.gritx.view.base.navigation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by lazy { SupportAppNavigator(this, R.id.mainContainerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationComponent.get().inject(this)

        if(savedInstanceState == null) {
            router.newRootChain(Screens.SignInScreen)
        }
    }

    override fun onBackPressed() {
        val currentScreen = supportFragmentManager.fragments.lastOrNull { it.isVisible }
        if (!(currentScreen is IBackPressed && currentScreen.onBackPressed())) {
            router.exit()
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
