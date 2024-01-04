package com.example.gamecharacters.home.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.gamecharacters.home.data.local.model.CharacterCached
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterCached")
    fun getCharacters(): Flow<List<CharacterCached>>

    @Upsert
    suspend fun saveCharacters(characters: List<CharacterCached>)

    @Query("SELECT * FROM CharacterCached WHERE id = :characterId")
    fun getCharacter(characterId: String): Flow<CharacterCached>
}
