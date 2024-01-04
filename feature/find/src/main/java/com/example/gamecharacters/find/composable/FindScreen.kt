package com.example.gamecharacters.find.composable
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

const val MAX_CHARACTER_ID = 100

@SuppressLint("RememberReturnType")
@Composable
fun FindScreen(modifier: Modifier = Modifier) {
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
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RandomCharacterButton(apiService, onCharacterLoaded = { character ->
                fullName = character.fullName
                title = character.title
                charImageURL = character.imageUrl
            })

            if (charImageURL.isNotEmpty()) {
                CharacterImage(charImageURL)
            }

            CharacterNameTextField(fullName, onValueChange = { fullName = it }, focusRequester)
            CharacterTitleTextField(title, onValueChange = { title = it })

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
    }
}

@Composable
private fun RandomCharacterButton(apiService: ApiService, onCharacterLoaded: (CharacterResponse) -> Unit) {
    Button(
        onClick = {
            apiService.getCharacterInfo((0..MAX_CHARACTER_ID).random()).enqueue(object :
                Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    if (response.isSuccessful) {
                        val character = response.body()
                        character?.let { onCharacterLoaded(it) }
                    }
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    // Handle errors if needed
                }
            })
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text("View random character")
    }
}

@Composable
private fun CharacterImage(charImageURL: String) {
    Image(
        painter = rememberImagePainter(charImageURL),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 20.dp)
    )
}

@Composable
private fun CharacterNameTextField(fullName: String, onValueChange: (String) -> Unit, focusRequester: FocusRequester) {
    BasicTextField(
        value = fullName,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
            .focusRequester(focusRequester)
    )
}

@Composable
private fun CharacterTitleTextField(title: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = title,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier.fillMaxWidth().padding(bottom = 36.dp)
    )
}
