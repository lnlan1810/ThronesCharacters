package com.example.gamecharacters.home.presentation.characterList.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gamecharacters.extensions.collectWithLifecycle
import com.example.gamecharacters.home.R
import com.example.gamecharacters.home.presentation.characterList.CharactersEvent
import com.example.gamecharacters.home.presentation.characterList.CharactersIntent
import com.example.gamecharacters.home.presentation.characterList.CharactersUiState
import com.example.gamecharacters.home.presentation.characterList.CharactersViewModel
import com.example.gamecharacters.navigation.AppBarState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.Flow

@Composable
fun CharactersRoute(
    onAppBarState: (AppBarState) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    HandleEvents(viewModel.event)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val title = stringResource(id = com.example.gamecharacters.base.R.string.app_name)

    LaunchedEffect(key1 = true) {
        onAppBarState(
            AppBarState(
                title = title,
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = "Info icon"
                        )
                    }
                }
            )
        )
    }

    CharactersScreen(
        uiState = uiState,
        onRefreshCharacters = {
            viewModel.acceptIntent(CharactersIntent.RefreshCharacters)
        },
        onCharacterClicked = {
            viewModel.acceptIntent(CharactersIntent.CharacterClicked(it))
        },
        modifier = modifier
    )
}

@Composable
fun CharactersScreen(
    uiState: CharactersUiState,
    onRefreshCharacters: () -> Unit,
    modifier: Modifier = Modifier,
    onCharacterClicked: (String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        // TODO: migrate from accompanist to built-in pull-to-refresh when added to Material3
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = uiState.isLoading),
            onRefresh = onRefreshCharacters,
            indicatorPadding = padding
        ) {
            if (uiState.characters.isNotEmpty()) {
                CharactersAvailableContent(
                    snackbarHostState = snackbarHostState,
                    uiState = uiState,
                    onCharacterClicked = onCharacterClicked
                )
            } else {
                CharactersNotAvailableContent(uiState = uiState)
            }
        }
    }
}

@Composable
private fun HandleEvents(events: Flow<CharactersEvent>) {
    events.collectWithLifecycle {
        when (it) {
            is CharactersEvent.ExampleEvent -> {}
            else -> {}
        }
    }
}

@Composable
private fun CharactersAvailableContent(
    snackbarHostState: SnackbarHostState,
    uiState: CharactersUiState,
    onCharacterClicked: (String) -> Unit
) {
    if (uiState.isError) {
        val errorMessage = stringResource(R.string.characters_error_refreshing)

        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = errorMessage
            )
        }
    }

    CharactersListContent(
        characterList = uiState.characters,
        onCharacterClicked = { onCharacterClicked(it) }
    )
}

@Composable
private fun CharactersNotAvailableContent(uiState: CharactersUiState) {
    when {
        uiState.isLoading -> CharactersLoadingPlaceHolder()
        uiState.isError -> CharactersErrorContent()
    }
}
