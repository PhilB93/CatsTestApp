package com.example.catstestapp.data.local.entity

import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageEntity (
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id_image")
    val id_image: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
) {
    fun toImage(): Image {
        return Image(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )
    }
}