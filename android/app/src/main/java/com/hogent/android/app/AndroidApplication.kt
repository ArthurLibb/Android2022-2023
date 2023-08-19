package com.hogent.android.app

import android.app.Application
import com.hogent.android.BuildConfig
import timber.log.Timber

class AndroidApplication : Application(){
    override fun onCreate(){
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}