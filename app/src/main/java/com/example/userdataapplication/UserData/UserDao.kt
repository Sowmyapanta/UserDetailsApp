package com.example.userdataapplication.UserData

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : User)

    @Update
    suspend fun updateUser(user : User)

    @Query("SELECT * FROM userData")
    fun readAllUsers(): LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User)

    @Query ("DELETE FROM USERDATA")
    fun deleteAllUsers()

}