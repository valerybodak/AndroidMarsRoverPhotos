package com.module.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.module.data.entities.db.MarsPhotoDbItem

@Database(entities = arrayOf(MarsPhotoDbItem::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): MarsPhotosDao
}