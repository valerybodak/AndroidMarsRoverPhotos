package com.module.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.module.data.entities.MarsPhotoData
import io.reactivex.Flowable

@Dao
interface ArticlesDao{

    @Query("Select * from photos")
    fun getAllArticles(): Flowable<List<MarsPhotoData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<MarsPhotoData>)

    @Query("DELETE FROM photos")
    fun clear()

}