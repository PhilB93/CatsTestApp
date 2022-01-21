package com.example.catstestapp.domain.model

import android.os.Parcelable
import com.example.catstestapp.presentation.model.ImageUI
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val height: Int?,
    val id_image: String?,
    val url: String?,
    val width: Int?
) : Parcelable
{
    fun toImageUI():ImageUI =
        ImageUI(
            height = height,
            id_image = id_image,
            url =url,
            width = width
        )
}
