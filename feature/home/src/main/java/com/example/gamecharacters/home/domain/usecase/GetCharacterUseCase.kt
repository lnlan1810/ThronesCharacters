package com.example.gamecharacters.home.domain.usecase

import com.example.gamecharacters.home.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import com.example.gamecharacters.extensions.resultOf
import com.example.gamecharacters.home.domain.model.Character

interface GetCharacterUseCase {
    suspend operator fun invoke(characterId: String): Flow<Result<Character>>
}

class GetCharacterUseCaseImpl(
    private val characterRepository: CharacterRepository
) : GetCharacterUseCase {
    override suspend fun invoke(characterId: String) =
        characterRepository
            .getCharacter(characterId)
            .map {
                resultOf { it }
            }
            .catch {
                emit(Result.failure(it))
            }
}
