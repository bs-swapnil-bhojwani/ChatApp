package com.swapnil.chatapp

import android.app.Application
import com.swapnil.chatapp.helpers.appModule
import com.swapnil.chatapp.helpers.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChatApp:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@ChatApp)
            modules(appModule, viewModelModule)
        }
    }
}