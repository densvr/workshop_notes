package com.danser.workshop4_login.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context
}
