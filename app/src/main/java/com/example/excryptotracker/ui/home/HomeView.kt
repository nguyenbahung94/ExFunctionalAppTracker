package com.example.excryptotracker.ui.home

import com.example.excryptotracker.model.CurrencyModel

interface HomeView {
    fun updateProgressVisibility(visible: Boolean)

    fun showInternetError()

    fun updateData(response: List<CurrencyModel>)

    fun showError(error: Throwable)

    fun disablePullToRefreshProgress()

    fun refreshList()
}