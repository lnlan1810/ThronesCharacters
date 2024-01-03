package com.example.gamecharacters.home.presentation.mapper

import com.example.gamecharacters.home.presentation.model.CharacterDisplayable
import com.example.gamecharacters.home.domain.model.Character

fun Character.toPresentationModel() = CharacterDisplayable(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl
)
