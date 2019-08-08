package com.module.domain.entities

data class MarsPhotoEntity(
        var id: Int,
        var name: String? = null,
        var description: String? = null,
        var url: String? = null,
        var category: String? = null)