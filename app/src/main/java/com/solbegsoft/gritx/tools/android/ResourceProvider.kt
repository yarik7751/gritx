package com.solbegsoft.gritx.tools.android

import android.content.Context
import android.content.res.Resources
import androidx.annotation.*
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceProvider @Inject constructor(private val context: Context) : IResourceProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    override fun getResources(): Resources {
        return context.resources
    }

    override fun getColor(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }

    override fun getDimensionPixelSize(dimenRes: Int): Int {
        return getResources().getDimensionPixelSize(dimenRes)
    }
}

/**
 * In some cases, you may want to retrieve a string or phoneNumber from application resources in the Presenter or domain layer .
 * but they should not interact directly with the Android framework.
 * To solve this problem, we can create a special resource Manager entity to access external resources.
 */
interface IResourceProvider {
    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String

    fun getResources(): Resources

    @ColorInt
    fun getColor(@ColorRes colorId: Int): Int

    @Px
    fun getDimensionPixelSize(@DimenRes dimenRes: Int): Int
}