package com.example.gamecharacters.home.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDisplayable(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val imageUrl: String
) : Parcelable
