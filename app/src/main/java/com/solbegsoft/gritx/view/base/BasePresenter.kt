package com.solbegsoft.gritx.view.base

import androidx.annotation.CallSuper
import com.solbegsoft.gritx.tools.android.IResourceProvider
import com.solbegsoft.gritx.tools.rx.IRxSchedulersProvider
import com.solbegsoft.gritx.tools.rx.RxTransformers
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import ru.terrakok.cicerone.Router
import java.lang.ref.WeakReference

abstract class BasePresenter<View : IBaseView>(
    protected val router: Router,
    protected val rxSchedulersProvider: IRxSchedulersProvider,
    protected val resourceProvider: IResourceProvider
) {

    private lateinit var weakView: WeakReference<View>
    protected lateinit var compositeDisposable: CompositeDisposable

    protected val viewState: View?
        get() = weakView.get()

    protected val isAttached
        get() = weakView.get() != null

    @CallSuper
    open fun onAttach(view: View) {
        weakView = WeakReference(view)
        compositeDisposable = CompositeDisposable()
    }

    @CallSuper
    open fun onDetach() {
        compositeDisposable.clear()
        weakView.clear()
    }

    protected fun startProgress() {
        viewState?.startProgress()
    }

    protected fun stopProgress() {
        viewState?.stopProgress()
    }

    protected open fun onThrowable(throwable: Throwable) {
        /*val message = throwable.getMessage(resourceProvider)

        Timber.e(throwable)
        Timber.e(message)

        showInfoMessage(InfoMessageViewModel(message = message, type = MessageType.ERROR))*/
    }

    protected open fun showInfoMessage(message: String) {
        //showInfoMessage(InfoMessageViewModel(message, MessageType.INFO))
    }

    protected open fun showWarningMessage(message: String) {
        //showInfoMessage(InfoMessageViewModel(message, MessageType.WARNING))
    }

    /*protected fun <E : InfoMessageViewModel> showInfoMessage(message: E) {
        viewState?.showInfoMessageViewModel(message)
    }*/

    open fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    protected fun showLoading(): Consumer<Disposable> {
        return Consumer { startProgress() }
    }


    protected fun hideLoading(): Action {
        return Action {
            run {
                stopProgress()
            }
        }
    }

    protected fun <T> getProgressSingleTransformer(): SingleTransformer<T, T> {
        return RxTransformers.applySingleBeforeAndAfter(showLoading(), hideLoading())
    }

    protected fun <T> getProgressObservableTransformer(): ObservableTransformer<T, T> {
        return RxTransformers.applyObservableBeforeAndAfter(showLoading(), hideLoading())
    }

    protected fun getProgressCompletableTransformer(): CompletableTransformer {
        return RxTransformers.applyCompletableBeforeAndAfter(showLoading(), hideLoading())
    }

    protected fun <T> getProgressMaybeTransformer(): MaybeTransformer<T, T> {
        return RxTransformers.applyMaybeBeforeAndAfter(showLoading(), hideLoading())
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }
}