package com.mvvmCoroutineKotlin.module

import com.mvvmCoroutineKotlin.networking.NetworkService
import com.mvvmCoroutineKotlin.roomDB.DBHelper
import com.mvvmCoroutineKotlin.scopes.PerActivity
import com.mvvmCoroutineKotlin.uiView.BaseView
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @PerActivity
    fun getViewModelFactory(
        networkService: NetworkService,
        dbHelper: DBHelper,
        baseView: BaseView
    ) = ViewModelFactory(networkService, dbHelper, baseView)

}