package ttnny.dev.android.rxjavabasic

import android.util.Log
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import ttnny.dev.android.rxjavabasic.MainActivity.Companion.TAG
import ttnny.dev.android.rxjavabasic.data.User
import java.lang.Exception

// Disposable
lateinit var disposable: Disposable

// Observable & Observer
fun createObservable(): Observable<Int> {
    return Observable.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                for (i in 0..100) {
                    emitter.onNext(i)
                }

                emitter.onComplete()
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun observerObservable(): Observer<Int> {
    return object : Observer<Int> {
        override fun onSubscribe(d: Disposable?) {
            d?.let { 
                disposable = d
            }

            Log.d(TAG, "onSubscribe")
        }

        override fun onNext(t: Int?) {
            Log.d(TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(TAG, "onError")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }
    }
}

// SingleObservable & SingleObserver
fun createSingleObservable(): Single<Int> {
    return Single.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                for (i in 0..100) { // only emits 0 since Single
                    emitter.onSuccess(i)
                }
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun observerSingle(): SingleObserver<Int> {
    return object : SingleObserver<Int> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onSuccess(t: Int?) {
            Log.d(TAG, "onSuccess: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(TAG, "onError")
        }
    }
}

// MaybeObservable & MaybeObserver
fun createMaybeObservable(): Maybe<List<User>> {
    return Maybe.just(userList)
}

fun observerMaybe(): MaybeObserver<List<User>> {
    return object : MaybeObserver<List<User>> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onSuccess(t: List<User>?) {
            t?.forEach { it ->
                Log.d(TAG, "onSuccess: $it")
            }
        }

        override fun onError(e: Throwable?) {
            Log.d(TAG, "onError")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }
    }
}

// Completable & CompletableObserver
fun createCompletable(): Completable {
    return Completable.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                Log.d(TAG, "Do something...")
                emitter.onComplete()
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun observerCompletable(): CompletableObserver {
    return object : CompletableObserver {
        override fun onSubscribe(d: Disposable?) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }

        override fun onError(e: Throwable?) {
            Log.d(TAG, "onError")
        }

    }
}

// Flowable & Observer
fun createFlowable1(): Flowable<Int> {
    return Flowable.range(1, 100)
}

fun createFlowable2(): Observable<Int> {
    return Observable.range(1, 10)
}