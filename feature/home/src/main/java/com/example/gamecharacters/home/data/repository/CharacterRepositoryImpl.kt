package com.example.gamecharacters.home.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import com.example.gamecharacters.home.data.local.dao.CharacterDao
import com.example.gamecharacters.home.data.mapper.toDomainModel
import com.example.gamecharacters.home.data.mapper.toEntityModel
import com.example.gamecharacters.home.data.remote.api.ThronesApi
import com.example.gamecharacters.home.domain.model.Character
import com.example.gamecharacters.home.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val thronesApi: ThronesApi,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getCharacters(): Flow<List<Character>> {
        return characterDao
            .getCharacters()
            .map { charactersCached ->
                charactersCached.map { it.toDomainModel() }
            }
            .onEach { rockets ->
                if (rockets.isEmpty()) {
                    refreshCharacters()
                }
            }
    }

    override suspend fun refreshCharacters() {
        thronesApi
            .getCharacters()
            .map {
                it.toDomainModel().toEntityModel()
            }
            .also {
                characterDao.saveCharacters(it)
            }
    }

    override fun getCharacter(characterId: String): Flow<Character> {
        return characterDao
            .getCharacter(characterId)
            .map { it.toDomainModel() }
    }
}

