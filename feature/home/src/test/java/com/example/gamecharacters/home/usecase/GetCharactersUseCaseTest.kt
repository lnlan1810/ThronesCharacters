package com.example.gamecharacters.home.usecase

import app.cash.turbine.test
import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.home.domain.usecase.GetCharactersUseCase
import com.example.gamecharacters.home.domain.usecase.getCharacters
import com.example.gamecharacters.home.generateTestCharacterFromDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.coroutines.cancellation.CancellationException

class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var objectUnderTest: GetCharactersUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpGetCharactersUseCase()
    }

    @Test
    fun `should wrap result with success if repository doesn't throw`() = runTest {
        val testCharactersFromDomain = listOf(generateTestCharacterFromDomain())
        coEvery { characterRepository.getCharacters() } returns flowOf(testCharactersFromDomain)

        objectUnderTest.invoke().test {
            val result = awaitItem()

            assertEquals(
                expected = Result.success(testCharactersFromDomain),
                actual = result
            )
            awaitComplete()
        }
    }

    @Test
    fun `should retry operation if repository throws IOException`() = runTest {
        val testException = IOException("Test message")
        val testCharactersFromDomain = listOf(generateTestCharacterFromDomain())
        coEvery { characterRepository.getCharacters() } throws testException andThen flowOf(
            testCharactersFromDomain
        )

        assertThrows<IOException> {
            objectUnderTest.invoke().test {
                val errorResult = awaitItem()

                assertEquals(
                    expected = Result.failure(testException),
                    actual = errorResult
                )

                val itemsResult = awaitItem()

                assertEquals(
                    expected = Result.success(testCharactersFromDomain),
                    actual = itemsResult
                )
            }
        }
    }

    @Test
    fun `should rethrow if repository throws CancellationException`() = runTest {
        coEvery { characterRepository.getCharacters() } throws CancellationException()

        assertThrows<CancellationException> {
            objectUnderTest.invoke()
        }
    }

    @Test
    fun `should wrap result with failure if repository throws other Exception`() = runTest {
        val testException = Exception("Test message")
        coEvery { characterRepository.getCharacters() } throws testException

        assertThrows<Exception> {
            objectUnderTest.invoke().test {
                val result = awaitItem()

                assertEquals(
                    expected = Result.failure(testException),
                    actual = result
                )
            }
        }
    }

    private fun setUpGetCharactersUseCase() {
        objectUnderTest = GetCharactersUseCase {
            getCharacters(characterRepository)
        }
    }
}
