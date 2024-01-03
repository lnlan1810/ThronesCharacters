package com.example.gamecharacters.home.repository

import com.example.gamecharacters.home.data.local.dao.CharacterDao
import com.example.gamecharacters.home.data.mapper.toDomainModel
import com.example.gamecharacters.home.data.mapper.toEntityModel
import com.example.gamecharacters.home.data.remote.api.ThronesApi
import com.example.gamecharacters.home.data.repository.CharacterRepositoryImpl
import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.home.generateTestCharacterFromDomain
import com.example.gamecharacters.home.generateTestCharacterFromRemote
import io.mockk.MockKAnnotations
import kotlinx.coroutines.flow.collect
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterRepositoryTest {

    @RelaxedMockK
    private lateinit var thronesApi: ThronesApi

    @RelaxedMockK
    private lateinit var characterDao: CharacterDao

    private lateinit var objectUnderTest: CharacterRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpCharacterRepository()
    }

    @Test
    fun `should refresh characters if local database is empty`() = runTest {
        every { characterDao.getCharacters() } returns flowOf(emptyList())

        objectUnderTest.getCharacters().collect()

        coVerifyOrder {
            thronesApi.getCharacters()
            characterDao.saveCharacters(any())
        }
    }

    @Test
    fun `should save mapped characters locally if retrieved from remote`() = runTest {
        val testCharactersFromRemote = listOf(generateTestCharacterFromRemote())
        val testCharactersToCache =
            testCharactersFromRemote.map { it.toDomainModel().toEntityModel() }
        coEvery { thronesApi.getCharacters() } returns testCharactersFromRemote

        objectUnderTest.refreshCharacters()

        coVerify { characterDao.saveCharacters(testCharactersToCache) }
    }

    @Test
    fun `should retrieve character from local database if valid id was given`() = runTest {
        every { characterDao.getCharacter(any()) } returns flowOf(
            generateTestCharacterFromDomain().toEntityModel()
        )

        objectUnderTest.getCharacter("0").collect()

        coVerify { characterDao.getCharacter("0") }
    }

    private fun setUpCharacterRepository() {
        objectUnderTest = CharacterRepositoryImpl(
            thronesApi,
            characterDao
        )
    }
}
