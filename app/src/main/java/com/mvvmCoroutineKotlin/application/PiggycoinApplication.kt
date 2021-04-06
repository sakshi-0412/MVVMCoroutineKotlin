package com.mvvmCoroutineKotlin.application

import android.app.Activity
import android.app.Application
import com.mvvmCoroutineKotlin.component.AppComponent
import com.mvvmCoroutineKotlin.component.DaggerAppComponent
import com.mvvmCoroutineKotlin.module.AppDBModule
import com.mvvmCoroutineKotlin.module.ContextModule
import com.mvvmCoroutineKotlin.module.NetworkModule
import com.mvvmCoroutineKotlin.utils.InternetConnectionMonitor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MvvmCoroutineKotlinApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    companion object {
        private lateinit var appComponent: AppComponent
        fun appComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
        InternetConnectionMonitor(this).enable()
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appDBModule(AppDBModule())
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}
