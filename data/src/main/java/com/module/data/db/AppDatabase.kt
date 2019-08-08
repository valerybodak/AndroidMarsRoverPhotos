package com.module.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.module.data.entities.MarsPhotoData

@Database(entities = arrayOf(MarsPhotoData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): MarsPhotosDao
}