package com.example.gamecharacters.home.domain.usecase

import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.home.domain.model.Character
import com.example.gamecharacters.extensions.resultOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

private const val RETRY_TIME_IN_MILLIS = 15_000L

fun interface GetCharactersUseCase : () -> Flow<Result<List<Character>>>


fun getCharacters(
    characterRepository: CharacterRepository
): Flow<Result<List<Character>>> =
    characterRepository
        .getCharacters()
        .map {
            resultOf { it }
        }
        .retryWhen { cause, _ ->
            if (cause is IOException) {
                emit(Result.failure(cause))

                delay(RETRY_TIME_IN_MILLIS)
                true
            } else {
                false
            }
        }
        .catch { // for other than IOException but it will stop collecting Flow
            emit(Result.failure(it))
        }
