package com.mvvmCoroutineKotlin.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.mvvmCoroutineKotlin.BR
import com.mvvmCoroutineKotlin.R
import com.mvvmCoroutineKotlin.databinding.ActivityDemoBinding
import com.mvvmCoroutineKotlin.module.ViewModelFactory
import com.mvvmCoroutineKotlin.uiView.DemoView
import com.mvvmCoroutineKotlin.viewModel.DemoViewModel
import kotlinx.android.synthetic.main.activity_demo.*
import javax.inject.Inject

class DemoActivity : BaseActivity<ActivityDemoBinding>(), DemoView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onLogin() {
        demoViewModel.login(etName.text.toString(), etEmail.text.toString(), etPassword.text.toString())
    }

    private lateinit var demoViewModel: DemoViewModel

    private lateinit var activityDemoBinding: ActivityDemoBinding

    override fun getViewModel() = demoViewModel

    override fun getLayoutId() = R.layout.activity_demo

    override fun getBindingVariable() = BR.viewModel

    override fun onLoginSuccess() {
        showMsg("Success")
        startActivity(Intent(this, Demo1Activity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)

        demoViewModel = ViewModelProviders.of(this, viewModelFactory).get(DemoViewModel::class.java)
        activityDemoBinding = bindViewData()
        activityDemoBinding.viewModel = demoViewModel
    }

    /*
    * Please use below credentails to test
    * kinal.m@latitudetechnolabs.com
    * 1234567
    * */

}
