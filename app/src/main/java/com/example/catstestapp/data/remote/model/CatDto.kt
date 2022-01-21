package com.example.catstestapp.data.remote.model

import com.example.catstestapp.data.local.entity.CatEntity
import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.model.Image

data class CatDto(
    val description: String,
    val id: String,
    val image: ImageDto,
    val name: String,
) {
    fun toCatEntity(): CatEntity {
        return CatEntity(
            description = description,
            id = id,
            image = image.toImageEntity(),
            name = name
        )
    }
}
    fun CatDto.toCat(): Cat {
        return Cat(
            description = description,
            id = id,
            image = image.toImage(),
            name = name
        )
    }

