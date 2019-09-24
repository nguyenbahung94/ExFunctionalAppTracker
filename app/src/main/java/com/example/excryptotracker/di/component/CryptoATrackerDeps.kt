package com.example.excryptotracker.di.component

import com.example.excryptotracker.di.module.AppModule
import com.example.excryptotracker.di.module.CryptoNetworkModule
import com.example.excryptotracker.MainActivity
import com.example.excryptotracker.ui.home.HomeActivity
import com.example.excryptotracker.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (CryptoNetworkModule::class)])
interface CryptoATrackerDeps {
    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)
}