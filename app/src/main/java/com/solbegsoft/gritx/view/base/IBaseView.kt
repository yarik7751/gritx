package com.solbegsoft.gritx.view.base

interface IBaseView {

    fun startProgress()

    fun stopProgress()

    //fun <E : InfoMessageViewModel> showInfoMessageViewModel(message: E)

    fun showToast(message: String)
}