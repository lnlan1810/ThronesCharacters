package com.example.gamecharacters.home.usecase

import app.cash.turbine.test
import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.home.domain.usecase.GetCharacterUseCase
import com.example.gamecharacters.home.domain.usecase.GetCharacterUseCaseImpl
import com.example.gamecharacters.home.generateTestCharacterFromDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals

class GetCharacterUseCaseTest {

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var objectUnderTest: GetCharacterUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpGetCharactersUseCase()
    }

    @Test
    fun `should wrap result with success if repository doesn't throw`() = runTest {
        val testCharacterFromDomain = generateTestCharacterFromDomain()
        coEvery { characterRepository.getCharacter(any()) } returns flowOf(testCharacterFromDomain)

        objectUnderTest("0").test {
            val result = awaitItem()

            assertEquals(
                expected = Result.success(testCharacterFromDomain),
                actual = result
            )
            awaitComplete()
        }
    }

    @Test
    fun `should rethrow if repository throws CancellationException`() = runTest {
        coEvery { characterRepository.getCharacter(any()) } throws CancellationException()

        assertThrows<CancellationException> {
            objectUnderTest("0")
        }
    }

    @Test
    fun `should wrap result with failure if repository throws other Exception`() = runTest {
        val testException = Exception("Test message")
        coEvery { characterRepository.getCharacter("0") } throws testException

        assertThrows<Exception> {
            objectUnderTest("0").test {
                val result = awaitItem()

                assertEquals(
                    expected = Result.failure(testException),
                    actual = result
                )
            }
        }
    }

    private fun setUpGetCharactersUseCase() {
        objectUnderTest = GetCharacterUseCaseImpl(characterRepository)
    }
}
