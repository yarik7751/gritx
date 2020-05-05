package com.solbegsoft.gritx.tools.rx

import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

object RxTransformers {

    fun applyCompletableBeforeAndAfter(before: Consumer<*>, after: Action): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream
                .doOnDispose(after)
                .doOnSubscribe(before as Consumer<in Disposable>?)
                .doOnTerminate(after)
        }
    }

    fun <T> applyObservableBeforeAndAfter(
        before: Consumer<Disposable>,
        after: Action
    ): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                .doOnSubscribe(before)
                .doOnDispose(after)
                .doOnTerminate(after)

        }
    }

    fun <T> applyMaybeBeforeAndAfter(
        before: Consumer<Disposable>,
        after: Action
    ): MaybeTransformer<T, T> {
        return MaybeTransformer { upstream ->
            upstream
                .doOnDispose(after)
                .doOnSubscribe(before)
                .doAfterTerminate(after)
        }
    }

    fun <T> applySingleBeforeAndAfter(
        before: Consumer<Disposable>,
        after: Action
    ): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream
                .doOnDispose(after)
                .doOnSubscribe(before)
                .doAfterTerminate(after)
        }
    }
}