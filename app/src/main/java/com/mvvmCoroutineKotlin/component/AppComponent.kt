package com.mvvmCoroutineKotlin.component

import com.mvvmCoroutineKotlin.application.MvvmCoroutineKotlinApplication
import com.mvvmCoroutineKotlin.module.AppDBModule
import com.mvvmCoroutineKotlin.module.NetworkModule
import com.mvvmCoroutineKotlin.module.ViewModule
import com.mvvmCoroutineKotlin.networking.NetworkService
import com.mvvmCoroutineKotlin.roomDB.DBHelper
import com.mvvmCoroutineKotlin.scopes.PerApplication
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class, AppDBModule::class])
interface AppComponent {
    fun inject(piggycoinApplication: MvvmCoroutineKotlinApplication)
    fun activityComponent(viewModule: ViewModule): ActivityComponent
    fun dbHelper(): DBHelper
    fun networkService(): NetworkService
}
