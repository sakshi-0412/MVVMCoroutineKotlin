package com.mvvmCoroutineKotlin.roomDB

import com.mvvmCoroutineKotlin.roomDB.entity.User
import com.mvvmCoroutineKotlin.scopes.PerApplication
import javax.inject.Inject

@PerApplication
class DBHelper @Inject constructor(var appDB: AppDB) {

    fun setLoggedInUser(user: User): List<Long> {
        return appDB.userDao().setLoggedInUser(user)
    }

    fun updateUser(user: User) {
        return appDB.userDao().updateUser(user)
    }

    fun getUser(): User {
        return appDB.userDao().getUser()
    }
}