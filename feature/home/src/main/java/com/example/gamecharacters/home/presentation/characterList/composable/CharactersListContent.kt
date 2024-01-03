package com.example.gamecharacters.home.presentation.characterList.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.example.gamecharacters.home.R
import com.example.gamecharacters.home.presentation.model.CharacterDisplayable

const val CHARACTER_DIVIDER_TEST_TAG = "characterDividerTestTag"

@Composable
fun CharactersListContent(
    characterList: List<CharacterDisplayable>,
    modifier: Modifier = Modifier,
    onCharacterClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_medium)
            )
    ) {
        itemsIndexed(
            items = characterList,
            key = { _, character -> character.id }
        ) { index, item ->
            CharacterItem(
                character = item,
                onCharacterClicked = { onCharacterClicked(item.id.toString()) }
            )

            if (index < characterList.lastIndex) {
                Divider(
                    modifier = Modifier.testTag(CHARACTER_DIVIDER_TEST_TAG),
                    color = Color.Transparent
                )
            }
        }
    }
}
