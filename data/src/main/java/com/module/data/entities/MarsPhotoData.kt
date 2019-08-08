package com.module.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mars_photos")
data class MarsPhotoData(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @SerializedName("img_src") var url: String? = null)