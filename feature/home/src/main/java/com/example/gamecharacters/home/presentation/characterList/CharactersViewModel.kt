package com.example.gamecharacters.home.presentation.characterList

import androidx.lifecycle.SavedStateHandle
import com.example.gamecharacters.base.BaseViewModel
import com.example.gamecharacters.home.domain.usecase.GetCharactersUseCase
import com.example.gamecharacters.home.domain.usecase.RefreshCharactersUseCase
import com.example.gamecharacters.home.presentation.mapper.toPresentationModel
import com.example.gamecharacters.navigation.NavigationDirections
import com.example.gamecharacters.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val refreshCharactersUseCase: RefreshCharactersUseCase,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    charactersInitialState: CharactersUiState
) : BaseViewModel<CharactersUiState, CharactersUiState.PartialState, CharactersEvent, CharactersIntent>(
    savedStateHandle,
    charactersInitialState
) {
    init {
        acceptIntent(CharactersIntent.GetCharacters)
    }

    override fun mapIntents(intent: CharactersIntent): Flow<CharactersUiState.PartialState> =
        when (intent) {
            is CharactersIntent.GetCharacters -> getCharacters()
            is CharactersIntent.RefreshCharacters -> refreshCharacters()
            is CharactersIntent.CharacterClicked -> characterClicked(intent.id)
        }

    override fun reduceUiState(
        previousState: CharactersUiState,
        partialState: CharactersUiState.PartialState
    ): CharactersUiState = when (partialState) {
        is CharactersUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )
        is CharactersUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            characters = partialState.list,
            isError = false
        )
        is CharactersUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true
        )
    }

    private fun getCharacters(): Flow<CharactersUiState.PartialState> = flow {
        getCharactersUseCase()
            .onStart {
                emit(CharactersUiState.PartialState.Loading)
            }
            .collect { result ->
                result
                    .onSuccess { characterList ->
                        emit(
                            CharactersUiState.PartialState.Fetched(
                                characterList.map { it.toPresentationModel() }
                            )
                        )
                    }
                    .onFailure {
                        emit(CharactersUiState.PartialState.Error(it))
                    }
            }
    }

    private fun refreshCharacters(): Flow<CharactersUiState.PartialState> = flow {
        refreshCharactersUseCase()
            .onFailure {
                emit(CharactersUiState.PartialState.Error(it))
            }
    }

    private fun characterClicked(id: String): Flow<CharactersUiState.PartialState> {
        navigationManager.navigate(
            NavigationDirections.CharacterDetailNavigation.characterDetail(id)
        )
        return emptyFlow()
    }
}
