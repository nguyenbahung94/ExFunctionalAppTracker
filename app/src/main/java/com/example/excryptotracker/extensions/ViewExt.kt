package com.example.excryptotracker.extensions

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

inline fun View.showSnackbar(
    snackbarText: String,
    timeLength: Int = Snackbar.LENGTH_LONG,
    listener: Snackbar.() -> Unit = {}
) = Snackbar.make(this, snackbarText, timeLength).apply {
    listener()
    show()
}

inline fun View.showSnackbar(
    @StringRes snackbarTextRes: Int,
    timeLength: Int = Snackbar.LENGTH_LONG,
    listener: Snackbar.() -> Unit = {}
) = Snackbar.make(this, snackbarTextRes, timeLength).apply {
    listener()
    show()
}

fun Window.blockTouchScreen() {
    this.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Window.unblockTouchScreen() {
    this.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}