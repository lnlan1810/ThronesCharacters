package com.example.gamecharacters.home.presentation.characterList.composable
/*

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamecharacters.extensions.Constants.SEARCH_BUTTON_TEXT_TAG
import com.example.gamecharacters.extensions.Constants.TEXT_FIELD_TEXT_TAG
import com.example.gamecharacters.ui.Purple80

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchComponent(onSearchClicked: (String) -> Unit) {
    val text = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(Modifier.fillMaxWidth().padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Card(modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp)) {
                OutlinedTextField(
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    placeholder = {
                        Text(text = "Enter search query", color = Color.LightGray, fontSize = 12.sp)
                    },
                    singleLine = true,
                    value = text.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Purple80,
                        unfocusedBorderColor = Color.Transparent,
                      //  background = Color.White,
                      //  textColor = Color.Black
                    ),
                    onValueChange = { text.value = it },
                    modifier = Modifier.testTag(TEXT_FIELD_TEXT_TAG),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            onSearchClicked(text.value)
                        }
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Card(shape = RoundedCornerShape(12.dp)) {
                IconButton(
                    modifier = Modifier.testTag(SEARCH_BUTTON_TEXT_TAG),
                    onClick = {
                        keyboardController?.hide()
                        onSearchClicked(text.value)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon", tint = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}
*/
