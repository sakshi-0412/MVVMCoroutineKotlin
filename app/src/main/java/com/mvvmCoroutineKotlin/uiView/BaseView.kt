package com.mvvmCoroutineKotlin.uiView

import android.support.annotation.LayoutRes
import com.mvvmCoroutineKotlin.viewModel.BaseViewModel

interface BaseView {
    fun showMsg(msgId: Int)

    fun showMsg(msg: String)

    fun hideKeyboard()

    fun onHandleException(e: Throwable)

    fun onSuccess(msg: String)

    fun onFailed(msg: String, error: Int)

    fun getViewModel(): BaseViewModel

    @LayoutRes
    fun getLayoutId(): Int

    fun getBindingVariable(): Int
}
