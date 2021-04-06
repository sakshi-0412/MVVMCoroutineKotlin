package com.mvvmCoroutineKotlin.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mvvmCoroutineKotlin.networking.NetworkService
import com.mvvmCoroutineKotlin.roomDB.DBHelper
import com.mvvmCoroutineKotlin.uiView.BaseView
import com.mvvmCoroutineKotlin.viewModel.BaseViewModel
import com.mvvmCoroutineKotlin.viewModel.DemoView1Model
import com.mvvmCoroutineKotlin.viewModel.DemoViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
    private val networkService: NetworkService,
    private val dbHelper: DBHelper,
    private val baseView: BaseView
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DemoViewModel::class.java) -> DemoViewModel(networkService, dbHelper, baseView) as T
            modelClass.isAssignableFrom(DemoView1Model::class.java) -> DemoView1Model(networkService, dbHelper, baseView) as T
            else -> BaseViewModel(networkService, dbHelper, baseView) as T
        }

    }
}