package com.example.catstestapp.presentation.model

import android.os.Parcelable
import com.example.catstestapp.domain.model.Cat
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatUI(
    val description: String?,
    val id: String,
    val image: ImageUI?,
    val name: String?,
) : Parcelable {
    fun toCat(): Cat = Cat(
        description = description,
        id = id,
        image = image?.toImage(),
        name = name
    )
}
