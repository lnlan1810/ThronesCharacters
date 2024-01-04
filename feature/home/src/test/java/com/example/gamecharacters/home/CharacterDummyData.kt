package com.example.gamecharacters.home

import com.example.gamecharacters.home.data.remote.model.CharacterResponse
import com.example.gamecharacters.home.domain.model.Character

internal fun generateTestCharacterFromRemote() = CharacterResponse(
    id = 0,
    firstName = "Daenerys",
    lastName = "Targaryen",
    fullName = "Daenerys Targaryen",
    title = "Mother of Dragons",
    family = "House Targaryen",
    image = "daenerys.jpg",
    imageUrl = "https://thronesapi.com/assets/images/daenerys.jpg"
)

internal fun generateTestCharacterFromDomain() = Character(
    id = 0,
    firstName = "Daenerys",
    lastName = "Targaryen",
    fullName = "Daenerys Targaryen",
    title = "Mother of Dragons",
    family = "House Targaryen",
    imageUrl = "https://thronesapi.com/assets/images/daenerys.jpg"
)
