package com.example.gamecharacters.home.presentation.characterDetail

sealed class CharacterDetailEvent {
    data class ExampleEvent(val value: String) : CharacterDetailEvent()
}
