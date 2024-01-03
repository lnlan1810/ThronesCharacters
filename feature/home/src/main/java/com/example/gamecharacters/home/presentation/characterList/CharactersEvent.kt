package com.example.gamecharacters.home.presentation.characterList

sealed class CharactersEvent {
    data class ExampleEvent(val value: String) : CharactersEvent()
}
