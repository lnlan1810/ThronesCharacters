package com.example.gamecharacters.home.presentation.characterList

sealed class CharactersIntent {
    object GetCharacters : CharactersIntent()
    object RefreshCharacters : CharactersIntent()
    data class CharacterClicked(val id: String) : CharactersIntent()
}
