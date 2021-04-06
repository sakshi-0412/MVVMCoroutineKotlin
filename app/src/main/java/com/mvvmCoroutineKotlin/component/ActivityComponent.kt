package com.mvvmCoroutineKotlin.component

import com.mvvmCoroutineKotlin.activity.Demo1Activity
import com.mvvmCoroutineKotlin.activity.DemoActivity
import com.mvvmCoroutineKotlin.module.ActivityModule
import com.mvvmCoroutineKotlin.module.ViewModule
import com.mvvmCoroutineKotlin.scopes.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ViewModule::class, ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: DemoActivity)
    fun inject(activity: Demo1Activity)
}
