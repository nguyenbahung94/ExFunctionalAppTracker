package com.example.excryptotracker.network

import com.example.excryptotracker.extensions.uiSubscribe
import com.example.excryptotracker.model.CurrencyModel
import io.reactivex.disposables.Disposable

class CryptoNetWorkService(private val netWorkService: NetworkServices) {
    fun getAllCurrency(
        onSuccess: (List<CurrencyModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return netWorkService.getAllCurrency()
            .uiSubscribe(onNext = { onSuccess(it) },
                onError = { onError(it) }
            )
    }
}