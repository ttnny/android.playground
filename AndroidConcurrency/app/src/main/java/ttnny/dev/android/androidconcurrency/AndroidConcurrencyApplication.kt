package ttnny.dev.android.androidconcurrency

import android.app.Application
import timber.log.Timber

class AndroidConcurrencyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}