package com.luiscosta.registration.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.luiscosta.registration.database.entity.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Single<List<User>>

    @Query("SELECT * FROM user WHERE id LIKE :userId LIMIT 1")
    fun getUserById(userId: Long): Single<User>

    @Insert
    fun insertUser(user: User): Single<Long>

    @Query("DELETE FROM user")
    fun deleteAll(): Completable
}