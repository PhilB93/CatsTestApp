package com.example.catstestapp.domain.model

import com.example.catstestapp.data.local.entity.ImageEntity
import com.example.catstestapp.presentation.model.ImageUI

data class Image(
    val height: Int?,
    val id_image: String?,
    val url: String?,
    val width: Int?
)
{
    fun toImageUI():ImageUI =
        ImageUI(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )

    fun toImageEntity(): ImageEntity =
        ImageEntity(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )
}
