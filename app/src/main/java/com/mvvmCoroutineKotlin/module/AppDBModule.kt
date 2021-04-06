package com.mvvmCoroutineKotlin.module

import android.arch.persistence.room.Room
import android.content.Context
import com.mvvmCoroutineKotlin.roomDB.AppDB
import com.mvvmCoroutineKotlin.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class AppDBModule {

    @Provides
    @PerApplication
    fun provideAppDatabase(context: Context): AppDB {
        return Room.databaseBuilder(context, AppDB::class.java, "MvvmCoroutineKotlinDB").fallbackToDestructiveMigration().build()
    }
}