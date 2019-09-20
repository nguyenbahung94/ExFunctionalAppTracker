package com.example.excryptotracker.ui.splash

import com.example.excryptotracker.network.NetWorkUtils

class SplashPresenter(private val splashView: SplashView, private val networkUtils: NetWorkUtils) {
    fun checkRequirement() {
        when {
            networkUtils.isConnected -> {
                splashView.navigationToHome()
            }
            else -> splashView.showInternetError()
        }
    }

}