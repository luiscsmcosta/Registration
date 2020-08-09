package com.luiscosta.registration.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luiscosta.registration.database.dao.UserDao
import com.luiscosta.registration.database.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}