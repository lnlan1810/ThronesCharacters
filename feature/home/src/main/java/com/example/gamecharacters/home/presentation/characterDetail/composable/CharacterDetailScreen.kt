package com.example.gamecharacters.home.presentation.characterDetail.composable

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gamecharacters.home.R
import com.example.gamecharacters.home.presentation.characterDetail.CharacterDetailUiState
import com.example.gamecharacters.home.presentation.characterDetail.CharacterDetailViewModel
import com.example.gamecharacters.navigation.AppBarState


const val CHARACTER_LOADING_TEST_TAG = "characterLoadingTestTag"

@Composable
fun CharacterDetailRoute(
    onAppBarState: (AppBarState) -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = uiState.character) {
        onAppBarState(
            AppBarState(
                title = uiState.character?.fullName ?: "",
                showNavigationIcon = true
            )
        )
    }

    CharacterDetailScreen(uiState = uiState)
}

@Composable
fun CharacterDetailScreen(
    uiState: CharacterDetailUiState,
    modifier: Modifier = Modifier
) {
    if (uiState.character != null) {
        CharacterDetails(character = uiState.character, modifier = modifier)
    } else {
        CharacterNotAvailableContent(uiState = uiState)
    }
}

@Composable
private fun CharacterNotAvailableContent(
    uiState: CharacterDetailUiState
) {
    when {
        uiState.isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier.testTag(CHARACTER_LOADING_TEST_TAG)
            )
        }
        uiState.isError -> {
            Text(
                text = stringResource(id = R.string.character_detail_error_fetching),
                color = Color.Red,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
