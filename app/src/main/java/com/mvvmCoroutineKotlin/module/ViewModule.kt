package com.mvvmCoroutineKotlin.module

import com.mvvmCoroutineKotlin.scopes.PerActivity
import com.mvvmCoroutineKotlin.uiView.BaseView
import dagger.Module
import dagger.Provides


@Module
class ViewModule(private val view: BaseView) {

    @PerActivity
    @Provides
    fun provideView(): BaseView {
        return view
    }
}