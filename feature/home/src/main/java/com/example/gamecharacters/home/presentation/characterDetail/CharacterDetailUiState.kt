package com.example.gamecharacters.home.presentation.characterDetail

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import com.example.gamecharacters.home.presentation.model.CharacterDisplayable

@Immutable
@Parcelize
data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val character: CharacterDisplayable? = null,
    val isError: Boolean = false
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()

        data class Fetched(val character: CharacterDisplayable) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}
