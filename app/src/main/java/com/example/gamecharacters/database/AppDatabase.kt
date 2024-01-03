package com.example.gamecharacters.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gamecharacters.home.data.local.dao.CharacterDao
import com.example.gamecharacters.home.data.local.model.CharacterCached

private const val DATABASE_VERSION = 1

@Database(
    entities = [CharacterCached::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
