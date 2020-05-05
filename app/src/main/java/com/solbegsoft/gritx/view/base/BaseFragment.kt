package com.solbegsoft.gritx.view.base

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.RestrictTo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.solbegsoft.gritx.tools.rx.AutoDisposable
import com.tbruyelle.rxpermissions2.RxPermissions
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), IBaseView,
    IBackPressed {

    @Inject
    lateinit var router: Router

    // scope name of the area that child view can use
    private lateinit var fragmentScopeName: String

    protected lateinit var scope: RestrictTo.Scope

    protected val autoDisposable by lazy { AutoDisposable() }

    private var instanceStateSaved: Boolean = false

    protected val rxPermissions by lazy { RxPermissions(this) }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        autoDisposable.bindTo(this.lifecycle)
        onAttachPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected open fun initView() {}

    override fun startProgress() {

    }

    override fun stopProgress() {

    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
    }

    /*override fun <E : InfoMessageViewModel> showInfoMessageViewModel(message: E) {
        router.showInfoMessage(message)
    }*/

    // This is android, baby!
    private fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) || // Because isRemoving == true for fragment in backstack on screen rotation
                ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

    abstract fun onAttachPresenter()
    abstract fun onDetachPresenter()

    // It will be valid only for 'onDestroy()' method
    private fun needCloseScope(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        if (needCloseScope()) {
            onDetachPresenter()
        }
    }

    override fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}