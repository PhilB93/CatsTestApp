package com.example.catstestapp.domain.model

import com.example.catstestapp.data.local.entity.CatEntity
import com.example.catstestapp.presentation.model.CatUI

data class Cat(
    val description: String?,
    val id: String,
    val image: Image?,
    val name: String?,
)
{
    fun toCatUI():CatUI
    = CatUI(
        description = description,
        id = id,
        image = image?.toImageUI(),
        name = name,
    )
    fun toCatEntity(): CatEntity
            = CatEntity(
        description = description,
        id = id,
        image = image?.toImageEntity(),
        name = name
    )
}
