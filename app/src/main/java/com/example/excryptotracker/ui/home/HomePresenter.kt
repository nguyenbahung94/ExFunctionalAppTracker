package com.example.excryptotracker.ui.home

import com.example.excryptotracker.extensions.safeDispose
import com.example.excryptotracker.network.CryptoNetWorkService
import com.example.excryptotracker.network.NetWorkUtils
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private val homeView: HomeView,
    private val service: CryptoNetWorkService,
    private val netWorkUtils: NetWorkUtils
) {
    private val disposable = CompositeDisposable()

    fun getAllCurrentcies() {
        when {
            netWorkUtils.isConnected -> {
                homeView.updateProgressVisibility(true)
                disposable.add(service.getAllCurrency(
                    onSuccess = { response ->
                        homeView.disablePullToRefreshProgress()
                        homeView.updateProgressVisibility(false)
                        if (response.isNotEmpty()) {
                            val sortedList = response.sortedWith(
                                compareBy {
                                    it.rank?.toInt()
                                }
                            )
                            homeView.updateData(sortedList)
                        }
                    },
                    onError = {
                        homeView.disablePullToRefreshProgress()
                        homeView.updateProgressVisibility(false)
                        homeView.showError(it)
                    }
                ))
            }
            else -> {
                homeView.disablePullToRefreshProgress()
                homeView.showInternetError()
            }
        }
    }

    fun onDestroy() {
        disposable.safeDispose()
    }
}