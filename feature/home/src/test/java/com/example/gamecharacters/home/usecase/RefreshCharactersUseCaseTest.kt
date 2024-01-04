package com.example.gamecharacters.home.usecase

import com.example.gamecharacters.home.domain.repository.CharacterRepository
import com.example.gamecharacters.home.domain.usecase.RefreshCharactersUseCase
import com.example.gamecharacters.home.domain.usecase.refreshCharacters
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals

class RefreshCharactersUseCaseTest {
    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var objectUnderTest: RefreshCharactersUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        setUpRefreshCharactersUseCase()
    }

    @Test
    fun `should wrap result with success if repository doesn't throw`() = runTest {
        coEvery { characterRepository.refreshCharacters() } just Runs

        val result = objectUnderTest.invoke()

        assertEquals(
            expected = Result.success(Unit),
            actual = result
        )
    }

    @Test
    fun `should rethrow if repository throws CancellationException`() = runTest {
        coEvery { characterRepository.refreshCharacters() } throws CancellationException()

        assertThrows<CancellationException> {
            objectUnderTest.invoke()
        }
    }

    @Test
    fun `should wrap result with failure if repository throws other Throwable`() = runTest {
        val testException = Throwable("Test message")
        coEvery { characterRepository.refreshCharacters() } throws testException

        assertThrows<Throwable> {
            val result = objectUnderTest.invoke()

            assertEquals(
                expected = Result.failure(testException),
                actual = result
            )
        }
    }

    private fun setUpRefreshCharactersUseCase() {
        objectUnderTest = RefreshCharactersUseCase {
            refreshCharacters(characterRepository)
        }
    }
}
