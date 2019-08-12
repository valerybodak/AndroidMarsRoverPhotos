package com.module.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mars_photos")
data class MarsPhotoDbItem(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var sol: Int = -1,
        var url: String = "",
        var cameraId: Int = -1,
        var cameraName: String = "",
        var cameraFullName: String = ""
)