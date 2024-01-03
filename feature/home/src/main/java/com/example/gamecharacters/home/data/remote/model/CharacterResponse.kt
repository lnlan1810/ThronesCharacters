package com.example.gamecharacters.home.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("firstName")
    val firstName: String = "",
    @SerialName("lastName")
    val lastName: String = "",
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("family")
    val family: String = "",
    @SerialName("image")
    val image: String = "",
    @SerialName("imageUrl")
    val imageUrl: String = ""
)
