package com.betterfilter.networking.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.betterfilter.networking.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}