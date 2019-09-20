package com.example.excryptotracker.extensions

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

fun CompositeDisposable?.safeDispose() {
    this?.clear()
}

fun <T : Any> disposableSubscriber(
    next: (T) -> Unit = {},
    error: (Throwable) -> Unit = {},
    complete: () -> Unit = {}
): DisposableSubscriber<T> {
    return object : DisposableSubscriber<T>() {
        override fun onComplete() {
            complete()
        }

        override fun onNext(response: T) {
            next(response)
        }

        override fun onError(e: Throwable) {
            error(e)
        }

    }
}
    fun <T : Any> Flowable<T>.transformCall(): Flowable<T> {
        return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .onErrorResumeNext(Function { Flowable.error { it } })
    }

    fun <T:Any> Flowable<T>.uiSubscribe(onNext:(T)->Unit={},
                                        onError:(Throwable)->Unit = {},
                                        onComplete:()->Unit = {}):Disposable{
        return this.transformCall()
            .subscribeWith(disposableSubscriber(
                onNext,onError,onComplete
            ))
}