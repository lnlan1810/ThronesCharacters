package com.example.gamecharacters.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamecharacters.ui.ThronesTheme
import com.example.gamecharacters.ui.darkModeSt
import com.example.gamecharacters.ui.darkModeState

import kotlinx.coroutines.flow.update
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier // Add modifier parameter
) {
    val darkModeOn = remember { mutableStateOf(darkModeState.value) }
    Surface(
        modifier = modifier.fillMaxSize(), // Use the provided modifier
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Bottom
            ) {

                LabeledDarkModeSwitch(
                    label = "Dark mode",
                    checked = darkModeOn.value,
                    onCheckedChanged = { isDark ->
                        darkModeOn.value = isDark
                        darkModeSt.update {
                            isDark
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun LabeledDarkModeSwitch(
    label: String,
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier // Add modifier parameter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth().padding(
            horizontal = 16.dp,
            vertical = 10.dp
        )
    ) {

        Text(text = label, style = MaterialTheme.typography.bodyLarge)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChanged
            )
        }
    }
}
