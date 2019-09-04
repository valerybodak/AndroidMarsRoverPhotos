package com.module.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.module.data.entities.db.MarsPhotoDbItem
import io.reactivex.Flowable

@Dao
interface MarsPhotosDao{

    @Query("Select * from mars_photos")
    fun getAllMarsPhotos(): Flowable<List<MarsPhotoDbItem>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMarsPhotos(photos: List<MarsPhotoDbItem>)

    @Query("DELETE FROM mars_photos")
    fun clear()

}