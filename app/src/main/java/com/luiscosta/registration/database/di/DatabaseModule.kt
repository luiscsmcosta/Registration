package com.luiscosta.registration.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.JournalMode
import com.luiscosta.registration.database.AppDatabase
import com.luiscosta.registration.database.dao.UserDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "dbName")
            .setJournalMode(JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}