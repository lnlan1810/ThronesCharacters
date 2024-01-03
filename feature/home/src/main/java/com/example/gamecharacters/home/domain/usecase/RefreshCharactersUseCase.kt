package com.example.gamecharacters.home.domain.usecase

import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.extensions.resultOf

fun interface RefreshCharactersUseCase : suspend () -> Result<Unit>

suspend fun refreshCharacters(
    characterRepository: CharacterRepository
): Result<Unit> = resultOf {
    characterRepository.refreshCharacters()
}