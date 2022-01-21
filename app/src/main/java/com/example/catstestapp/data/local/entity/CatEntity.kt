package com.example.catstestapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.model.Image

@Entity
data class CatEntity(
    val description: String?,
    @PrimaryKey
    val id: String,
    @Embedded
    val image: ImageEntity?,
    val name: String?,
) {
    fun toCat(): Cat {
        return Cat(
            id = id,
            name = name,
            description = description,
            image = image?.toImage()
        )
    }
}