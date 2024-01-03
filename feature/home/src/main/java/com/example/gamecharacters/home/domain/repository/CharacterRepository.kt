package com.example.gamecharacters.home.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.gamecharacters.home.domain.model.Character

interface CharacterRepository {
    fun getCharacters(): Flow<List<Character>>
    suspend fun refreshCharacters()
    fun getCharacter(characterId: String): Flow<Character>
}
