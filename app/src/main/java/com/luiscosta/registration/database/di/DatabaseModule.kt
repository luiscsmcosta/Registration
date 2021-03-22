package com.luiscosta.registration.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.JournalMode
import com.luiscosta.registration.database.AppDatabase
import com.luiscosta.registration.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "dbName")
            .setJournalMode(JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}