package com.example.excryptotracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.excryptotracker.di.component.CryptoATrackerDeps
import com.example.excryptotracker.di.component.DaggerCryptoATrackerDeps
import com.example.excryptotracker.di.module.AppModule

class MainActivity : AppCompatActivity() {
    lateinit var cryptoDeps: CryptoATrackerDeps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cryptoDeps = DaggerCryptoATrackerDeps.builder()
            .appModule(AppModule(this))
            .build()
        cryptoDeps.inject(this)
    }

}
