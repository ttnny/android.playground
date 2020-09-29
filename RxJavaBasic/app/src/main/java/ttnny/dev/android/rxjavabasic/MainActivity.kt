package ttnny.dev.android.rxjavabasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import ttnny.dev.android.rxjavabasic.data.User
import ttnny.dev.android.rxjavabasic.data.UserProfile

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Operators
       justOperator()
       fromOperator()
       fromIterableOperator()
       rangeOperator().subscribe(
           { Log.d(TAG, "onNext: $it") },
           { Log.d(TAG, "onError: $it") },
           { Log.d(TAG, "onComplete") }
       )
       repeatOperator().subscribe(
           { Log.d(TAG, "onNext: $it") },
           { Log.d(TAG, "onError: $it") },
           { Log.d(TAG, "onComplete") }
       )
       intervalOperator().subscribe(
           { Log.d(TAG, "onNext: $it") },
           { Log.d(TAG, "onError: $it") },
           { Log.d(TAG, "onComplete") }
       )
       timerOperator().subscribe(
           {
               Log.d(TAG, "onNext: $it")
               Log.d(TAG, "Testing")
           },
           { Log.d(TAG, "onError: $it") },
           { Log.d(TAG, "onComplete") }
       )
       createOperator().subscribe(
           { Log.d(TAG, "onNext: $it") },
           { Log.d(TAG, "onError: $it") },
           { Log.d(TAG, "onComplete") }
       )
       filterOperator()
           .filter {
               it.age > 18
           }
           .subscribe(
               { Log.d(TAG, "onNext: $it") },
               { Log.d(TAG, "onError: $it") },
               { Log.d(TAG, "onComplete") }
           )
       lastOperator()
           .last(User(0, "user0", 0)) // if empty list, return this user0 as default
           .subscribe(
               { Log.d(TAG, "onNext: $it") },
               { Log.d(TAG, "onError: $it") }
           )
       distinctOperator()
           .distinct {
               it.age
           }
           .subscribe(
               { Log.d(TAG, "onNext: $it") },
               { Log.d(TAG, "onError: $it") },
               { Log.d(TAG, "onComplete") }
           )
       skipOperator()
           .skip(2)
           .subscribe(
               { Log.d(TAG, "onNext: $it") },
               { Log.d(TAG, "onError: $it") },
               { Log.d(TAG, "onComplete") }
           )
       bufferOperator()
           .buffer(3) // emits 3 items at a time
           .subscribe(
               { Log.d(TAG, "onNext: $it") },
               { Log.d(TAG, "onError: $it") },
               { Log.d(TAG, "onComplete") }
           )
       mapOperator()
           .map {
               UserProfile(it.id, it.name, it.age, "image URL")
           }
           .subscribe(
               {
                   Log.d(TAG, "onNext: $it") // Log out UserProfile, not User
               },
               { Log.d(TAG, "onError: $it") },
               { Log.d(TAG, "onComplete") }
           )
       flatMapOperator()
           .flatMap {
               getUserProfile(it.id)
           }
           .subscribe(
               {
                   Log.d(TAG, "onNext: $it") // Log out UserProfile, not User
               },
               { Log.d(TAG, "onError: $it") },
               { Log.d(TAG, "onComplete") }
           )

        // Obversables & Observers
        createObservable().subscribe(observerObservable())
        createSingleObservable().subscribe(observerSingle())
        createMaybeObservable().subscribe(observerMaybe())
    }
}