package com.example.gamecharacters.home.data.remote.api

import com.example.gamecharacters.home.data.remote.model.CharacterResponse
import retrofit2.http.GET

interface ThronesApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharacterResponse>
}
