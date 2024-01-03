package com.example.gamecharacters.home.data.mapper

import com.example.gamecharacters.home.data.local.model.CharacterCached
import com.example.gamecharacters.home.data.remote.model.CharacterResponse
import com.example.gamecharacters.home.domain.model.Character

fun CharacterResponse.toDomainModel() = Character(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl
)

fun CharacterCached.toDomainModel() = Character(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl
)

fun Character.toEntityModel() = CharacterCached(
    id = id,
    firstName = firstName,
    lastName = lastName,
    fullName = fullName,
    title = title,
    family = family,
    imageUrl = imageUrl
)
