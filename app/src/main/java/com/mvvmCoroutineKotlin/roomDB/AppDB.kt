package com.mvvmCoroutineKotlin.roomDB

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mvvmCoroutineKotlin.roomDB.dao.UserDao
import com.mvvmCoroutineKotlin.roomDB.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}
