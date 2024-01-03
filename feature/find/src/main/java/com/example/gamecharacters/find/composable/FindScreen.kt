package com.example.gamecharacters.find.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.gamecharacters.find.api.ApiService
import com.example.gamecharacters.find.model.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("RememberReturnType")
@Composable
fun FindScreen() {
    val focusRequester = remember { FocusRequester() }
    var charImageURL by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://thronesapi.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    apiService.getCharacterInfo((0..100).random()).enqueue(object :
                        Callback<CharacterResponse> {
                        override fun onResponse(
                            call: Call<CharacterResponse>,
                            response: Response<CharacterResponse>
                        ) {
                            if (response.isSuccessful) {
                                val character = response.body()
                                if (character != null) {
                                    fullName = character.fullName
                                    title = character.title
                                    charImageURL = character.imageUrl
                                }
                            }
                        }

                        override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                        }
                    })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("View random character")
            }

            if (charImageURL.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(charImageURL),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 20.dp)

                )
            }
            BasicTextField(
                value = fullName,
                onValueChange = { fullName = it },

                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .focusRequester(focusRequester)
            )

            BasicTextField(
                value = title,
                onValueChange = { title = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.fillMaxWidth().padding(bottom = 36.dp)
            )
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
@Preview(showBackground = true)
fun GetInfoScreenPreview() {
    FindScreen()
}
