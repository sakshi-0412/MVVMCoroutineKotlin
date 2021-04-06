package com.mvvmCoroutineKotlin.module

import android.content.Context
import com.mvvmCoroutineKotlin.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    @PerApplication
    fun providesContext(): Context {
        return context
    }
}
