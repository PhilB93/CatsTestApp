package com.example.catstestapp.presentation.model

import com.example.catstestapp.domain.model.Image

data class CatUI(
    val description: String?,
    val id: String,
    val image: ImageUI?,
    val name: String?,
)
{

}