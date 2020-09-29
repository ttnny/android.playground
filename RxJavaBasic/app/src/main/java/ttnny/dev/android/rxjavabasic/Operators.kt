package ttnny.dev.android.rxjavabasic

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import ttnny.dev.android.rxjavabasic.data.User
import ttnny.dev.android.rxjavabasic.data.UserProfile
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
val array1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
val array2 = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120)
val userList = mutableListOf(
    User(1, "user1", 15),
    User(2, "user2", 18),
    User(3, "user3", 20),
    User(4, "user4", 21),
    User(5, "user5", 23),
    User(6, "user6", 22),
    User(7, "user7", 20),
    User(7, "user7", 20)
)

val userProfileList = mutableListOf(
    UserProfile(1, "user1", 15, "//imageURL/user1"),
    UserProfile(2, "user2", 18, "//imageURL/user2"),
    UserProfile(3, "user3", 20, "//imageURL/user3"),
    UserProfile(4, "user4", 21, "//imageURL/user4"),
    UserProfile(5, "user5", 23, "//imageURL/user5"),
    UserProfile(6, "user6", 23, "//imageURL/user6"),
    UserProfile(7, "user7", 22, "//imageURL/user7"),
    UserProfile(8, "user8", 22, "//imageURL/user8")
)

fun justOperator() {
    val observable = Observable.just(list) // max 10 items

    val observer = object : Observer<List<Int>> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onNext(t: List<Int>?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(MainActivity.TAG, "onError")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }
    }

    observable.subscribe(observer)
}

fun fromOperator() {
    val observable = Observable.fromArray(array1, array2)

    val observer = object : Observer<Array<Int>> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onNext(t: Array<Int>?) {
            Log.d(MainActivity.TAG, "onNext: ${Arrays.toString(t)}")
        }

        override fun onError(e: Throwable?) {
            Log.d(MainActivity.TAG, "onError")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }
    }

    observable.subscribe(observer)
}

fun fromIterableOperator() {
    val observable = Observable.fromIterable(list) // this emits the items in the sequence

    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable?) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onNext(t: Int?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable?) {
            Log.d(MainActivity.TAG, "onError")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }
    }

    observable.subscribe(observer)
}

fun rangeOperator(): Observable<Int> {
    return Observable.range(1, 10)
}

fun repeatOperator(): Observable<Int> {
    return Observable.range(1, 5).repeat(2)
}

fun intervalOperator(): Observable<Long> {
    return Observable.interval(3, 1, TimeUnit.SECONDS).take(10)
}

fun timerOperator(): Observable<Long> {
    return Observable.timer(3, TimeUnit.SECONDS)
}

fun createOperator(): Observable<Int> {
    return Observable.create {
        try {
            for (i in list) {
                it.onNext(i * 3)
            }

            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    }
}

fun filterOperator(): Observable<User> {
    return Observable.fromIterable(userList)
}

fun lastOperator(): Observable<User> {
    return Observable.fromIterable(userList)
}

fun distinctOperator(): Observable<User> {
    return Observable.fromIterable(userList)
}

fun skipOperator(): Observable<Int> {
    return Observable.range(1, 7)
}

fun bufferOperator(): Observable<User> {
    return Observable.fromIterable(userList)
}

fun mapOperator(): Observable<User> {
    return Observable.fromIterable(userList)
}

fun flatMapOperator(): Observable<User> {
    return Observable.fromIterable(userList)
}

fun getUserProfile(id: Long): Observable<UserProfile> {
    return Observable.fromIterable(userProfileList)
        .filter {
            it.id == id
        }
}