package com.example.gamecharacters.find.api

import com.example.gamecharacters.find.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Characters/{id}")
    fun getCharacterInfo(@Path("id") id: Int): Call<CharacterResponse>
}
