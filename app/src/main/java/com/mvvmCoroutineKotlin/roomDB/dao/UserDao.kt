package com.mvvmCoroutineKotlin.roomDB.dao

import android.arch.persistence.room.*
import com.mvvmCoroutineKotlin.roomDB.entity.User

@Dao
interface UserDao {
    @Transaction
    fun setLoggedInUser(loggedInUser: User): List<Long> {
        deleteUser(loggedInUser)
        return insertUserData(loggedInUser)
    }

    @Delete
    fun deleteUser(vararg user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(vararg user: User): List<Long>

    @Query("SELECT * FROM user")
    fun getUser(): User

    @Update
    fun updateUser(vararg user: User)
}