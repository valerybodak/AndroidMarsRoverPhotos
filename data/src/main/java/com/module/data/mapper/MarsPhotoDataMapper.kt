package com.module.data.mapper

import com.module.data.entities.response.MarsPhotoResponseData
import com.module.data.entities.response.MarsPhotoResponseItem
import com.module.data.entities.db.MarsPhotoDbItem
import com.module.domain.entities.MarsPhotoEntity
import com.module.domain.entities.MarsPhotoSourcesEntity

class MarsPhotoDataMapper {

    fun mapToEntity(data: MarsPhotoResponseData?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
        photos = mapListResponseItems(data?.photos)
    )

    fun mapToEntity(photos: List<MarsPhotoDbItem>?): MarsPhotoSourcesEntity? = MarsPhotoSourcesEntity(
        photos = mapListDbItems(photos)
    )
    private fun mapListResponseItems(photos: List<MarsPhotoResponseItem>?)
            : List<MarsPhotoEntity> = photos?.map { mapPhotoResponseItemToEntity(it) } ?: emptyList()

    private fun mapListDbItems(photos: List<MarsPhotoDbItem>?)
            : List<MarsPhotoEntity> = photos?.map { mapPhotoDbItemToEntity(it) } ?: emptyList()

    private fun mapPhotoResponseItemToEntity(item: MarsPhotoResponseItem): MarsPhotoEntity = MarsPhotoEntity(
        id = item.id,
        url = item.url,
        sol = item.sol,
        cameraId = item.camera.id,
        cameraName = item.camera.name,
        cameraFullName = item.camera.fullName
    )

    private fun mapPhotoDbItemToEntity(item: MarsPhotoDbItem): MarsPhotoEntity = MarsPhotoEntity(
        id = item.id,
        url = item.url,
        sol = item.sol,
        cameraId = item.cameraId,
        cameraName = item.cameraName,
        cameraFullName = item.cameraFullName
    )

    fun mapEntityToDbEntity(entity: MarsPhotoEntity): MarsPhotoDbItem =
        MarsPhotoDbItem(
            id = entity.id,
            sol = entity.sol,
            url = entity.url,
            cameraId = entity.cameraId,
            cameraName = entity.cameraName,
            cameraFullName = entity.cameraFullName
        )
}