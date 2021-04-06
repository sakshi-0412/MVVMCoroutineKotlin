package com.mvvmCoroutineKotlin.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.mvvmCoroutineKotlin.BR
import com.mvvmCoroutineKotlin.R
import com.mvvmCoroutineKotlin.databinding.ActivityDemo1Binding
import com.mvvmCoroutineKotlin.module.ViewModelFactory
import com.mvvmCoroutineKotlin.viewModel.DemoView1Model
import javax.inject.Inject

class Demo1Activity : BaseActivity<ActivityDemo1Binding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var demoView1Model: DemoView1Model

    private lateinit var activityDemo1Binding: ActivityDemo1Binding

    override fun getViewModel() = demoView1Model

    override fun getLayoutId() = R.layout.activity_demo1

    override fun getBindingVariable() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)

        demoView1Model = ViewModelProviders.of(this, viewModelFactory).get(DemoView1Model::class.java)
        activityDemo1Binding = bindViewData()
        activityDemo1Binding.viewModel = demoView1Model

        demoView1Model.getUser()
    }

    override fun onDestroy() {
        demoView1Model.onDestroy()
        super.onDestroy()
    }
}
