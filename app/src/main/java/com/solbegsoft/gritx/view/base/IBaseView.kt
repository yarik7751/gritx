package com.solbegsoft.gritx.view.base

interface IBaseView {

    fun startProgress()

    fun stopProgress()

    //fun <E : InfoMessageViewModel> showInfoMessageViewModel(message: E)

    fun openSignInScreen(onSignInCredentialed: (result: Boolean) -> Unit)

    fun showToast(message: String)
}