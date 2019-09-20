package com.example.excryptotracker.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import org.jetbrains.anko.appcompat.v7.Appcompat

fun Context.getColorCompat(@ColorRes colorId: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ContextCompat.getColor(this, colorId)
    } else this.resources.getColor(colorId)

fun Context.getDrawableCompat(@DrawableRes drawableId: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.resources.getDrawable(drawableId, null)
    } else AppCompatResources.getDrawable(this, drawableId)

fun Context.getConnectivityManager(): ConnectivityManager =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.convertDptoPx(dp: Int): Int {
    return Math.round(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this.resources.displayMetrics
        )
    )
}