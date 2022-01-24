package com.example.catstestapp.presentation.model

import android.os.Parcelable
import com.example.catstestapp.domain.model.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUI(
    val height: Int?,
    val id_image: String?,
    val url: String?,
    val width: Int?
): Parcelable
{
    fun toImage(): Image =
        Image(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )
}

