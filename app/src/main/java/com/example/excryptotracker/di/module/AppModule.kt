package com.example.excryptotracker.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}