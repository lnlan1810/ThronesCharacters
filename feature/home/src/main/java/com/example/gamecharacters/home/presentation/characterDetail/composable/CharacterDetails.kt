package com.example.gamecharacters.home.presentation.characterDetail.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.gamecharacters.home.R
import com.example.gamecharacters.ui.Typography
import com.example.gamecharacters.home.presentation.model.CharacterDisplayable

@Composable
fun CharacterDetails(
    character: CharacterDisplayable,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.dimen_small)
        ),
        modifier = modifier
            .fillMaxSize()
            .padding(
                dimensionResource(id = R.dimen.dimen_medium)
            )
    ) {
        Text(
            text = character.fullName,
            style = Typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        AsyncImage(
            model = character.imageUrl,
            contentDescription = stringResource(id = R.string.character_image_content_description),
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 10))
                .fillMaxWidth()
        )

        CharacterInfo(
            character = character,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CharacterInfo(
    character: CharacterDisplayable,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.dimen_small)
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_small)
            )
        ) {
            Text(
                text = stringResource(
                    id = R.string.character_first_name,
                    character.firstName
                ),
                style = Typography.bodyMedium
            )

            Text(
                text = stringResource(
                    id = R.string.character_last_name,
                    character.lastName
                ),
                style = Typography.bodyMedium
            )

            Text(
                text = stringResource(
                    id = R.string.character_full_name,
                    character.fullName
                ),
                style = Typography.bodyMedium
            )

            Text(
                text = stringResource(
                    id = R.string.character_title,
                    character.title
                ),
                style = Typography.bodyMedium
            )

            Text(
                text = stringResource(
                    id = R.string.character_family,
                    character.family
                ),
                style = Typography.bodyMedium
            )
        }
    }
}
