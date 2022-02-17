package com.example.userdataapplication.UserData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{

        @Volatile
        private var Instance : UserDatabase? = null

        fun getDatabase (context : Context): UserDatabase {
            val tempins = Instance
            if(tempins !=null)
            {
                return tempins
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, UserDatabase::class.java,
                    "userDatabase"
                ).build()
                Instance = instance
                return instance
            }
        }

    }


}