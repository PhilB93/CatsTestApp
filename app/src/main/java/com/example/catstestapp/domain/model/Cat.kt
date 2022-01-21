package com.example.catstestapp.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catstestapp.presentation.model.CatUI
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cat(
    val description: String?,
    val id: String,
    val image: Image?,
    val name: String?,
) : Parcelable
{
    fun toCatUI():CatUI
    = CatUI(
        description = description,
        id = id,
        image = image?.toImageUI(),
        name = name
    )

}
