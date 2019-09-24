package com.example.excryptotracker

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.excryptotracker.di.component.CryptoATrackerDeps
import com.example.excryptotracker.di.component.DaggerCryptoATrackerDeps
import com.example.excryptotracker.di.module.AppModule

class MainActivity : Application() {
    lateinit var cryptoDeps: CryptoATrackerDeps

    override fun onCreate() {
        super.onCreate()
        cryptoDeps = DaggerCryptoATrackerDeps.builder()
            .appModule(AppModule(this))
            .build()
        cryptoDeps.inject(this)
    }
}
