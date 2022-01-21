package com.example.catstestapp.data.remote.model

import com.example.catstestapp.data.local.entity.ImageEntity
import com.example.catstestapp.domain.model.Image

data class ImageDto (
    val height: Int?,
    val id_image: String?,
    val url: String?,
    val width: Int?
)
{
    fun toImageEntity(): ImageEntity {
        return ImageEntity(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )
    }
}
    fun ImageDto.toImage(): Image {
        return Image(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )
    }

